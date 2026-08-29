package com.christianco.springBoot.service;

import com.christianco.springBoot.dto.ProductDTO;
import com.christianco.springBoot.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.christianco.springBoot.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productoRepository;

    public Page<ProductDTO> getAll(Pageable pageable) {
        return productoRepository.findAll(pageable).map(this::convertToDto);
    }

    public ProductDTO getById(Long id) {
        Product producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto con ID " + id + " no encontrado"));
        return convertToDto(producto);
    }

    public ProductDTO saveProduct(ProductDTO dto) {
        Product producto = new Product();
        producto.setName(dto.getName());
        producto.setPrice(dto.getPrice());
        Product guardado = productoRepository.save(producto);
        return convertToDto(guardado);
    }

    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto con ID " + id + " no encontrado"));
        
        producto.setName(dto.getName());
        producto.setPrice(dto.getPrice());
        Product actualizado = productoRepository.save(producto);
        return convertToDto(actualizado);
    }

    public void deleteProduct(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto con ID " + id + " no encontrado");
        }
        productoRepository.deleteById(id);
    }

    private ProductDTO convertToDto(Product producto) {
        ProductDTO dto = new ProductDTO();
        dto.setId(producto.getId());
        dto.setName(producto.getName());
        dto.setPrice(producto.getPrice());
        return dto;
    }
}