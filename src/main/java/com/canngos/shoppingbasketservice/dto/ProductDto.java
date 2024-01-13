package com.canngos.shoppingbasketservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {
    private String name;
    private Double price;
    private String description;
    private String type;
    private int quantity;
}
