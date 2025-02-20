package com.antonadjei.eCommerce.services;


import com.antonadjei.eCommerce.dtos.product.ProductRequestDTO;
import com.antonadjei.eCommerce.dtos.product.ProductResponseDTO;
import com.antonadjei.eCommerce.enums.Type;
import com.antonadjei.eCommerce.exceptions.ProductAlreadyExistsException;
import com.antonadjei.eCommerce.exceptions.ProductNotFoundException;
import com.antonadjei.eCommerce.exceptions.TypeDoesNotExistException;
import com.antonadjei.eCommerce.models.Product;
import com.antonadjei.eCommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final TypeService typeService;

    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> list = productRepository.findAll();
        return ResponseEntity.ok().body(ProductResponseDTO.toProductResponseListDTO(list));
    }

    public ResponseEntity<ProductResponseDTO> getProductDetails(Long productID) {
        Optional<Product> product = productRepository.findById(productID);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(String.format("Product with id: <%d> was not found.", productID));
        }
        return ResponseEntity.ok().body(ProductResponseDTO.toProductResponseDTO(product.get()));
    }

    public ResponseEntity<List<ProductResponseDTO>> addProduct(List<ProductRequestDTO> products) {
        List<Product> savedProducts = new ArrayList<>();
        for (ProductRequestDTO productData: products) {
            // check if product already exists
            Optional<Product> foundProduct = productRepository.findByNameIgnoreCase(productData.getName());
            if (foundProduct.isPresent()) {
                throw new ProductAlreadyExistsException(String.format("Product with name: %s already exists", productData.getName()));
            }
            // validate the Type
            if (!typeService.validateProductType(productData.getType())) {
                throw new TypeDoesNotExistException(String.format(
                        "Type with name: <%s> does not exist. Please use one of the following types: %s",
                        productData.getType(),
                        typeService.getAllTypes()
                ));
            }
            Product product = Product.builder()
                    .name(productData.getName())
                    .type(Type.valueOf(productData.getType()))
                    .imageUrl(productData.getImageUrl())
                    .price(productData.getPrice())
                    .quantity(productData.getQuantity())
                    .build();
            productRepository.save(product);
            savedProducts.add(product);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ProductResponseDTO.toProductResponseListDTO(savedProducts)
        );
    }
}
