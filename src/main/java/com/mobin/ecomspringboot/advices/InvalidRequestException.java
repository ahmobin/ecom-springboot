package com.mobin.ecomspringboot.advices;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

}
