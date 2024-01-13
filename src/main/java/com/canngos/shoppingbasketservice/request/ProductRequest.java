package com.canngos.shoppingbasketservice.request;

import com.canngos.shoppingbasketservice.entity.ProductType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Name field cannot be empty")
    private String name;
    @NotNull(message = "Price field cannot be empty")
    private Double price;
    private String description;
    private ProductType type;
    @Min(value = 1, message = "Quantity field cannot be less than 1")
    private int quantity;
}
