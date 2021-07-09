package com.leratoletsepe.intellectsacademyapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IaNotFoundException extends RuntimeException {

    public IaNotFoundException(String message) {
        super(message);
    }
}