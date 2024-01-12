package com.canngos.shoppingbasketservice.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Name field cannot be empty")
    private String name;
    @NotBlank(message = "Last name field cannot be empty")
    private String lastName;
    @NotBlank(message = "Email field cannot be empty")
    @Email(message = "Email should be valid")
    private String email;
    @Min(value = 6, message = "Password should be at least 6 characters long")
    private String password;
    private String address;

}
