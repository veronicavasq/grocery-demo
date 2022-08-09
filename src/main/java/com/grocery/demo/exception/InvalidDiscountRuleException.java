package com.grocery.demo.exception;

public class InvalidDiscountRuleException extends GroceryException{

    public InvalidDiscountRuleException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
