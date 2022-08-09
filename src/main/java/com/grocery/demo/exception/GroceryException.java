package com.grocery.demo.exception;

import java.util.HashMap;
import java.util.Map;

public class GroceryException extends Exception {

    private final ErrorCode errorCode;

    private final Map<String, Object> additionalData;

    public GroceryException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.additionalData = new HashMap<>();
        this.additionalData.put("message", message);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getAdditionalData() {
        return this.additionalData;
    }

}
