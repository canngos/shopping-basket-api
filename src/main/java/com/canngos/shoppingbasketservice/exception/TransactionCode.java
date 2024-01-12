package com.canngos.shoppingbasketservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TransactionCode {
    SUCCESS(100, "Success", HttpStatus.OK),
    USER_NOT_FOUND(101, "User not found", HttpStatus.NOT_FOUND),
    INVALID_CREDENTIALS(102, "Invalid credentials", HttpStatus.BAD_REQUEST);

    private final int id;
    private final String code;
    private final HttpStatus httpStatus;

    TransactionCode(int id, String code, HttpStatus httpStatus) {
        this.id = id;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
