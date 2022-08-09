package com.grocery.demo.exception;

public enum ErrorCode {

    UNSUPPORTED_RULE_TYPE("001"),
    UNSUPPORTED_CALCULATOR_TYPE("002"),
    INVALID_RULE_TYPE("003"),
    INVALID_ORDER_EXCEPTION("004"),
    INVALID_DISCOUNT_TYPE("005"),;


    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
