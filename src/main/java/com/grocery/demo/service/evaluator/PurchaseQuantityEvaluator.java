package com.grocery.demo.service.evaluator;

import com.grocery.demo.model.Discount;
import com.grocery.demo.model.DiscountRule;
import com.grocery.demo.model.DiscountRuleType;
import com.grocery.demo.model.OrderItem;

public class PurchaseQuantityEvaluator implements IRuleEvaluator {

    @Override
    public boolean isValidRule(DiscountRule discountRule) {
        return DiscountRuleType.PURCHASE_QUANTITY_IN_RANGE == discountRule.getType();
    }

    @Override
    public boolean isApplicable(Discount discount, OrderItem orderItem) {
        DiscountRule discountRule = discount.getDiscountRule();

        if (discountRule.getMaximum() != null && discountRule.getMinimum() != null) {
            return discountRule.getMaximum() >= orderItem.getQuantity() && discountRule.getMinimum() <= orderItem.getQuantity();
        }

        if (discountRule.getMaximum() == null && discountRule.getMinimum() != null) {
            return discountRule.getMinimum() <= orderItem.getQuantity();
        }
        return false;
    }


}
