package com.canngos.shoppingbasketservice.response.body;

import com.canngos.shoppingbasketservice.dto.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseBody {

    private List<ProductDto> products;
}
