package com.grocery.demo.calculator;

import com.grocery.demo.exception.GroceryException;
import com.grocery.demo.model.Discount;
import com.grocery.demo.model.DiscountRule;
import com.grocery.demo.model.DiscountRuleType;
import com.grocery.demo.model.OrderItem;
import com.grocery.demo.service.evaluator.PurchaseQuantityEvaluator;
import com.grocery.demo.util.OrderTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PurchaseQuantityEvaluatorTest {

    private final PurchaseQuantityEvaluator evaluator = new PurchaseQuantityEvaluator();

    @Test
    public void isValidRuleForEvaluatorOk() {
        DiscountRule rule = OrderTestUtil.buildRule(DiscountRuleType.PURCHASE_QUANTITY_IN_RANGE);
        Assertions.assertTrue(this.evaluator.isValidRule(rule));
    }

    @Test
    public void verifyFalseConditionApplied() throws GroceryException {
        Discount discount = OrderTestUtil.buildDiscount(DiscountRuleType.PURCHASE_QUANTITY_IN_RANGE);
        Assertions.assertFalse(this.evaluator.verifyRule(discount, OrderTestUtil.buildOrderItem()));
    }

    @Test
    public void verifyConditionAppliedOk() {
        OrderItem item = OrderTestUtil.buildOrderItem();
        Discount discount = OrderTestUtil.buildDiscount(DiscountRuleType.PURCHASE_QUANTITY_IN_RANGE);
        discount.setCategory(item.getArticle().getCategory());
        Assertions.assertTrue(this.evaluator.isApplicable(discount, item));
    }
}
