package com.antonadjei.eCommerce.services;


import com.antonadjei.eCommerce.dtos.ProductResponseDTO;
import com.antonadjei.eCommerce.models.Product;
import com.antonadjei.eCommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> list = productRepository.findAll();
        return ResponseEntity.ok().body(ProductResponseDTO.toProductResponseListDTO(list));
    }
}
