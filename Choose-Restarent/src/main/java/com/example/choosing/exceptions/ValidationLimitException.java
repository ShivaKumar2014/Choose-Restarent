package com.example.choosing.exceptions;

public class ValidationLimitException extends RuntimeException{
    public ValidationLimitException(String exception) {
        super(exception);
    }
}
