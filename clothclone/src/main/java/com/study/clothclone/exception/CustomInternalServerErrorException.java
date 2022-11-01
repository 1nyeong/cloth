package com.study.clothclone.exception;

public class CustomInternalServerErrorException extends RuntimeException {
    public CustomInternalServerErrorException(String message){
        super(message);
    }
}

