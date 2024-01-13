package com.canngos.shoppingbasketservice.service;

import com.canngos.shoppingbasketservice.entity.Product;
import com.canngos.shoppingbasketservice.request.ProductRequest;
import com.canngos.shoppingbasketservice.response.DefaultMessageResponse;
import com.canngos.shoppingbasketservice.response.ProductResponse;

public interface ProductService {

    ProductResponse getProducts();
    Product getProduct(Long id);
    DefaultMessageResponse addProduct(ProductRequest productRequest);
    DefaultMessageResponse updateProduct(Long id, ProductRequest productRequest);
    DefaultMessageResponse deleteProduct(Long id);
}
