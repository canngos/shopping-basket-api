package com.canngos.shoppingbasketservice.response.body;

import com.canngos.shoppingbasketservice.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseBody {

    private List<Product> products;
}
