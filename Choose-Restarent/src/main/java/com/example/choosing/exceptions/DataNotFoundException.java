package com.example.choosing.exceptions;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String exception) {
        super(exception);
    }
}
