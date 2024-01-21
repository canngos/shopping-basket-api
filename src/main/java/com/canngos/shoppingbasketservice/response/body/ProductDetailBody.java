package com.canngos.shoppingbasketservice.response.body;

import com.canngos.shoppingbasketservice.dto.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailBody {

    private ProductDto product;
}
