package com.grocery.demo.exception;

public class InvalidDiscountCalculatorException extends GroceryException{

    public InvalidDiscountCalculatorException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
