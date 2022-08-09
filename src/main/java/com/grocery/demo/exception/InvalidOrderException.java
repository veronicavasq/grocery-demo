package com.grocery.demo.exception;

public class InvalidOrderException extends GroceryException{

    public InvalidOrderException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
