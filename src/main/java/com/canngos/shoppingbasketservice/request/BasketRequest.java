package com.canngos.shoppingbasketservice.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketRequest {
    @NotNull
    private Long productId;
    @NotNull
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
}
