package com.canngos.shoppingbasketservice.service;

import com.canngos.shoppingbasketservice.request.BasketRequest;
import com.canngos.shoppingbasketservice.response.DefaultMessageResponse;

public interface BasketService {

    DefaultMessageResponse addToBasket(String token, BasketRequest basketRequest);
}
