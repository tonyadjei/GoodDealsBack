package com.antonadjei.eCommerce.controllers;


import com.antonadjei.eCommerce.dtos.product.ProductRequestDTO;
import com.antonadjei.eCommerce.dtos.product.ProductResponseDTO;
import com.antonadjei.eCommerce.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productID}")
    public ResponseEntity<ProductResponseDTO> getProductDetails(@PathVariable Long productID) {
        return productService.getProductDetails(productID);
    }

    @Secured({"ADMIN", "SUPER_ADMIN"})
    @PostMapping("")
    public ResponseEntity<List<ProductResponseDTO>> addProduct(@Valid @RequestBody List<ProductRequestDTO> productData) {
        return productService.addProduct(productData);
    }
}
