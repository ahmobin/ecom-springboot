package com.mobin.ecomspringboot.exceptions;

public class UnwantedRequestException extends RuntimeException{
    public UnwantedRequestException(String message){
        super(message);
    }
}
