package com.canngos.shoppingbasketservice.service;

import com.canngos.shoppingbasketservice.request.LoginRequest;
import com.canngos.shoppingbasketservice.request.RegisterRequest;
import com.canngos.shoppingbasketservice.response.DefaultMessageResponse;
import com.canngos.shoppingbasketservice.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);
    DefaultMessageResponse register(RegisterRequest registerRequest);
}
