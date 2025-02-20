package com.antonadjei.eCommerce.dtos.product;


import com.antonadjei.eCommerce.enums.Type;
import com.antonadjei.eCommerce.models.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String name;
    private double price;
    private String type;
    private String imageUrl;
    private int quantity;

    public static ProductResponseDTO toProductResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .type(product.getType().toString())
                .imageUrl(product.getImageUrl())
                .quantity(product.getQuantity())
                .build();
    }

    public static List<ProductResponseDTO> toProductResponseListDTO(List<Product> products) {
        List<ProductResponseDTO> list = new ArrayList<>();
        for (Product product: products) {
            list.add(
                    ProductResponseDTO.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .type(product.getType().toString())
                            .imageUrl(product.getImageUrl())
                            .quantity(product.getQuantity())
                            .build()
            );
        }
        return list;
    }
}
