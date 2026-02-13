package com.joaoguilhermmy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcpetion extends RuntimeException {
    public ResourceNotFoundExcpetion(String msg) {
        super(msg);
    }
}
