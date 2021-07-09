package com.leratoletsepe.intellectsacademyapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IaAuthException extends RuntimeException{

    public IaAuthException(String message){
        super(message);
    }
}