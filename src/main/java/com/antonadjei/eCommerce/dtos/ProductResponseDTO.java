package com.antonadjei.eCommerce.dtos;


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
    private Double price;
    private Type type;
    private Integer quantity;

    public static ProductResponseDTO toProductResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .type(product.getType())
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
                            .type(product.getType())
                            .quantity(product.getQuantity())
                            .build()
            );
        }
        return list;
    }
}
