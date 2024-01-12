package com.canngos.shoppingbasketservice.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Email field cannot be empty")
    private String email;
    @NotBlank(message = "Password field cannot be empty")
    private String password;
}
