package com.canngos.shoppingbasketservice.response.body;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseBody {
    private String token;
    private Date expirationDate;
}
