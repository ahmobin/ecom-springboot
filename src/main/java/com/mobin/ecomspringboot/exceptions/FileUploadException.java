package com.mobin.ecomspringboot.exceptions;

public class FileUploadException extends RuntimeException{
    public FileUploadException(String message){
        super(message);
    }
}
