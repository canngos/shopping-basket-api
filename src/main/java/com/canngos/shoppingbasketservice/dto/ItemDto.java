package com.canngos.shoppingbasketservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
