package com.grocery.demo.service.evaluator;

import com.grocery.demo.model.Discount;
import com.grocery.demo.model.DiscountRule;
import com.grocery.demo.model.DiscountRuleType;
import com.grocery.demo.model.OrderItem;

import java.time.Duration;
import java.time.LocalDateTime;

public class StorageTimeEvaluator implements IRuleEvaluator {

    @Override
    public boolean isValidRule(DiscountRule discountRule) {
        return DiscountRuleType.STORAGE_TIME == discountRule.getType();
    }

    @Override
    public boolean isApplicable(Discount discount, OrderItem orderItem) {
        DiscountRule discountRule = discount.getDiscountRule();
        long storageDays = Duration.between(orderItem.getArticle().getLastEntryDate(), LocalDateTime.now()).toDays();
        return discountRule.getMinimum() <= storageDays && discountRule.getMaximum() >= storageDays;
    }


}
