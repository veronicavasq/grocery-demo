package com.grocery.demo.service.processor;

import com.grocery.demo.model.Discount;
import com.grocery.demo.model.DiscountType;
import com.grocery.demo.model.OrderItem;

import java.math.BigDecimal;

public class OfferDiscountCalculator implements IDiscountCalculator {

    @Override
    public boolean isValidType(Discount discount) {
        return DiscountType.OFFER_AMOUNT == discount.getDiscountType();
    }

    @Override
    public BigDecimal getDiscountAmount(Discount discount, OrderItem orderItem) {
        int offerUnit = (int) (orderItem.getQuantity() / discount.getMinPurchasedQuantity());
        return discount.getDiscountUnit().multiply(BigDecimal.valueOf(offerUnit));
    }
}
