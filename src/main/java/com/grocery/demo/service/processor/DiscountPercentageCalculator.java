package com.grocery.demo.service.processor;

import com.grocery.demo.model.Discount;
import com.grocery.demo.model.DiscountType;
import com.grocery.demo.model.OrderItem;

import java.math.BigDecimal;

public class DiscountPercentageCalculator implements IDiscountCalculator {
    @Override
    public boolean isValidType(Discount discount) {
        return DiscountType.PERCENTAGE == discount.getDiscountType();
    }

    @Override
    public BigDecimal getDiscountAmount(Discount discount, OrderItem orderItem) {
        BigDecimal percentage = discount.getDiscountUnit().divide(BigDecimal.valueOf(100));
        return orderItem.getArticle().getUnitPrice().multiply(percentage).multiply(BigDecimal.valueOf(orderItem.getQuantity()));
    }
}
