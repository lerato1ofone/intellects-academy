package com.leratoletsepe.intellectsacademyapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IaBadRequestException extends RuntimeException {

    public IaBadRequestException(String message) {
        super(message);
    }
}