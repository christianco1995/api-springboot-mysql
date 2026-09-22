package com.christianco.springBoot.controller;

import com.christianco.springBoot.dto.ProductDTO;
import com.christianco.springBoot.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productoService;

	@GetMapping
	public ResponseEntity<Page<ProductDTO>> list(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sort) {
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		
		return ResponseEntity.ok(productoService.getAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(productoService.getById(id));
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO dto) {
		return new ResponseEntity<>(productoService.saveProduct(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductDTO dto) {
		return ResponseEntity.ok(productoService.updateProduct(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
		productoService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
