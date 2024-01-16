package com.canngos.shoppingbasketservice.response.body;

import com.canngos.shoppingbasketservice.dto.ItemDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BasketResponseBody {

    private List<ItemDto> items;
    private BigDecimal totalPrice;
}
