package com.christianco.springBoot.service;

import com.christianco.springBoot.dto.ProductRequestDTO;
import com.christianco.springBoot.dto.ProductResponseDTO;
import com.christianco.springBoot.entity.Product;
import com.christianco.springBoot.exception.ResourceNotFoundException;
import com.christianco.springBoot.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(Long id) {
        Product product = getProductEntityById(id);
        return mapToDTO(product);
    }

    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setCategory(requestDTO.getCategory());
        product.setPrice(requestDTO.getPrice());
        product.setStock(requestDTO.getStock());
        
        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        Product product = getProductEntityById(id);
        
        product.setName(requestDTO.getName());
        product.setCategory(requestDTO.getCategory());
        product.setPrice(requestDTO.getPrice());
        product.setStock(requestDTO.getStock());
        
        Product updatedProduct = productRepository.save(product);
        return mapToDTO(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProductEntityById(id);
        productRepository.delete(product);
    }
    
    public Product getProductEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + id));
    }

    private ProductResponseDTO mapToDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        return dto;
    }
}
