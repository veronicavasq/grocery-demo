package com.grocery.demo.service.evaluator;

import com.grocery.demo.exception.ErrorCode;
import com.grocery.demo.exception.GroceryException;
import com.grocery.demo.exception.InvalidDiscountRuleException;
import com.grocery.demo.model.Discount;
import com.grocery.demo.model.DiscountRule;
import com.grocery.demo.model.OrderItem;

public interface IRuleEvaluator {

    boolean isValidRule(DiscountRule discountRule);

    default boolean verifyRule(Discount discount, OrderItem orderItem) throws GroceryException {
        if (!isValidRule(discount.getDiscountRule())) {
            throw new InvalidDiscountRuleException(ErrorCode.INVALID_RULE_TYPE, "Invalid discount rule");
        }

        if (discount.getCategory().getId() != orderItem.getArticle().getCategory().getId()) {
            return false;
        }

        if (discount.getOrigenCountry() != null && !discount.getOrigenCountry().equals(orderItem.getArticle().getOrigenCountry())) {
            return false;
        }

        return isApplicable(discount, orderItem);
    }

    boolean isApplicable(Discount discount, OrderItem orderItem);

}
