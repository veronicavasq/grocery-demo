package com.grocery.demo.service.calculator;

import com.grocery.demo.exception.ErrorCode;
import com.grocery.demo.exception.GroceryException;
import com.grocery.demo.exception.InvalidDiscountCalculatorException;
import com.grocery.demo.model.Discount;
import com.grocery.demo.model.OrderItem;

import java.math.BigDecimal;

public interface IDiscountCalculator {

    boolean isValidType(Discount discount);

    default BigDecimal calculateDiscount(Discount discount, OrderItem orderItem) throws GroceryException {
        if (!isValidType(discount)) {
            throw new InvalidDiscountCalculatorException(ErrorCode.INVALID_DISCOUNT_TYPE, "Invalid discount type");
        }
        return getDiscountAmount(discount, orderItem);
    }

    BigDecimal getDiscountAmount(Discount discount, OrderItem orderItem);
}
