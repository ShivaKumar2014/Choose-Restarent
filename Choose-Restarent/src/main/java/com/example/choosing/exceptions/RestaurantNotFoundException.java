package com.example.choosing.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String exception) {
        super(exception);
    }
}