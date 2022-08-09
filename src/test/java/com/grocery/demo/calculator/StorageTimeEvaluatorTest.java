package com.grocery.demo.calculator;

import com.grocery.demo.exception.GroceryException;
import com.grocery.demo.model.Discount;
import com.grocery.demo.model.DiscountRule;
import com.grocery.demo.model.DiscountRuleType;
import com.grocery.demo.model.OrderItem;
import com.grocery.demo.service.evaluator.StorageTimeEvaluator;
import com.grocery.demo.util.OrderTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StorageTimeEvaluatorTest {

    private final StorageTimeEvaluator evaluator = new StorageTimeEvaluator();

    @Test
    public void isValidRuleForEvaluatorOk() {
        DiscountRule rule = OrderTestUtil.buildRule(DiscountRuleType.STORAGE_TIME);
        Assertions.assertTrue(this.evaluator.isValidRule(rule));
    }

    @Test
    public void isValidRuleForEvaluatorFalse() {
        DiscountRule rule = OrderTestUtil.buildRule(DiscountRuleType.PURCHASE_QUANTITY_IN_RANGE);
        Assertions.assertFalse(this.evaluator.isValidRule(rule));
    }

    @Test
    public void verifyFalseConditionApplied() throws GroceryException {
        Discount discount = OrderTestUtil.buildDiscount(DiscountRuleType.STORAGE_TIME);
        OrderItem item = OrderTestUtil.buildOrderItem();
        Assertions.assertFalse(this.evaluator.verifyRule(discount, item));
    }

    @Test
    public void verifyConditionAppliedOk() {
        OrderItem item = OrderTestUtil.buildOrderItem();
        Discount discount = OrderTestUtil.buildDiscount(DiscountRuleType.STORAGE_TIME);
        discount.setCategory(item.getArticle().getCategory());
        Assertions.assertTrue(this.evaluator.isApplicable(discount, item));
    }
}
