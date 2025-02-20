package com.antonadjei.eCommerce.dtos.product;

import com.antonadjei.eCommerce.enums.Type;
import com.antonadjei.eCommerce.models.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    @NotBlank
    private String name;
    @Min(value = 1)
    private double price;
    @NotBlank
    private String type;
    @NotBlank
    private String imageUrl;
    @Min(value = 1)
    private int quantity;
}
