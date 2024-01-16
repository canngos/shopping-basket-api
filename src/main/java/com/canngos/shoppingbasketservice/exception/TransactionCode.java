package com.canngos.shoppingbasketservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TransactionCode {
    SUCCESS(100, "Success", HttpStatus.OK),
    USER_NOT_FOUND(101, "User not found", HttpStatus.NOT_FOUND),
    INVALID_CREDENTIALS(102, "Invalid credentials", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTS(103, "Email already exists", HttpStatus.BAD_REQUEST),
    TOKEN_INVALID(104, "Provided token is invalid", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED(105, "Provided token is expired", HttpStatus.BAD_REQUEST),
    PRODUCT_ALREADY_EXIST(106, "Product already exist", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(107, "Product not found", HttpStatus.NOT_FOUND),
    NOT_ENOUGH_STOCK(108, "Not enough stock", HttpStatus.BAD_REQUEST);

    private final int id;
    private final String code;
    private final HttpStatus httpStatus;

    TransactionCode(int id, String code, HttpStatus httpStatus) {
        this.id = id;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
