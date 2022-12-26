package com.mobin.ecomspringboot.exceptions;

public class DuplicateCurrencyException extends RuntimeException{
    public DuplicateCurrencyException(String message) {
        super(message);
    }
}
