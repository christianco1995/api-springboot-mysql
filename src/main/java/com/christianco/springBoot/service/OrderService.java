package com.christianco.springBoot.service;

import com.christianco.springBoot.dto.OrderItemRequestDTO;
import com.christianco.springBoot.dto.OrderItemResponseDTO;
import com.christianco.springBoot.dto.OrderRequestDTO;
import com.christianco.springBoot.dto.OrderResponseDTO;
import com.christianco.springBoot.entity.Order;
import com.christianco.springBoot.entity.OrderItem;
import com.christianco.springBoot.entity.OrderStatus;
import com.christianco.springBoot.entity.Product;
import com.christianco.springBoot.entity.User;
import com.christianco.springBoot.exception.BadRequestException;
import com.christianco.springBoot.exception.ResourceNotFoundException;
import com.christianco.springBoot.repository.OrderRepository;
import com.christianco.springBoot.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO requestDTO) {
        // a) Buscar el usuario autenticado
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con email: " + userEmail));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        
        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        // b) Iterar sobre los productos solicitados
        for (OrderItemRequestDTO itemRequest : requestDTO.getItems()) {
            // c) Validar que el producto exista
            Product product = productService.getProductEntityById(itemRequest.getProductId());
            
            // c) Validar que el stock sea suficiente
            if (product.getStock() < itemRequest.getQuantity()) {
                throw new BadRequestException("Stock insuficiente para el producto: " + product.getName());
            }
            
            // d) Restar el stock del producto
            product.setStock(product.getStock() - itemRequest.getQuantity());
            // (The product will be updated in the DB automatically because of @Transactional, or we could explicitly call save)
            
            // Crear el OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice().doubleValue()); // Assuming product price is BigDecimal
            
            orderItems.add(orderItem);
            
            // e) Calcular el precio total de la orden
            totalAmount += (product.getPrice().doubleValue() * itemRequest.getQuantity());
        }

        order.setItems(orderItems);
        order.setTotal(totalAmount);

        // f) Guardar la Order y sus OrderItem
        Order savedOrder = orderRepository.save(order);

        return mapToOrderResponseDTO(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getOrdersHistory() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return orderRepository.findByUserEmail(userEmail).stream()
                .map(this::mapToOrderResponseDTO)
                .collect(Collectors.toList());
    }

    private OrderResponseDTO mapToOrderResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus().name());
        dto.setTotal(order.getTotal());
        
        List<OrderItemResponseDTO> itemDTOs = order.getItems().stream().map(item -> {
            OrderItemResponseDTO itemDTO = new OrderItemResponseDTO();
            itemDTO.setId(item.getId());
            itemDTO.setProductId(item.getProduct().getId());
            itemDTO.setProductName(item.getProduct().getName());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setQuantity(item.getQuantity());
            return itemDTO;
        }).collect(Collectors.toList());
        
        dto.setItems(itemDTOs);
        return dto;
    }
}
