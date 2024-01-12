package com.canngos.shoppingbasketservice.response.base;

import com.canngos.shoppingbasketservice.exception.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseResponse<T> {
    private BaseBody<T> body;
    private Status status;

}
