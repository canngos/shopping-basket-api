package com.canngos.shoppingbasketservice.dto;

import com.canngos.shoppingbasketservice.entity.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private ProductType type;
    private int quantity;
}
