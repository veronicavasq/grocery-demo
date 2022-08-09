package com.grocery.demo.Evaluator;

import com.grocery.demo.exception.GroceryException;
import com.grocery.demo.exception.InvalidDiscountCalculatorException;
import com.grocery.demo.model.Discount;
import com.grocery.demo.model.DiscountRuleType;
import com.grocery.demo.model.DiscountType;
import com.grocery.demo.model.OrderItem;
import com.grocery.demo.service.calculator.DiscountPercentageCalculator;
import com.grocery.demo.util.OrderTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class DiscountPercentageCalculatorTest {

    DiscountPercentageCalculator calculator = new DiscountPercentageCalculator();

    @Test
    public void isValidDiscountTypeOk() {
        Discount discount = OrderTestUtil.buildDiscount(DiscountType.PERCENTAGE);
        Assertions.assertTrue(this.calculator.isValidType(discount));
    }

    @Test
    public void isValidDiscountTypeFalse() {
        Discount discount = OrderTestUtil.buildDiscount(DiscountType.FREE_UNITS);
        Assertions.assertThrows(InvalidDiscountCalculatorException.class, () -> this.calculator.calculateDiscount(discount, new OrderItem()));
    }

    @Test
    public void calculateDiscountAmount() throws GroceryException {
        OrderItem item = OrderTestUtil.buildOrderItem();
        Discount discount = OrderTestUtil.buildDiscount(DiscountRuleType.STORAGE_TIME);
        discount.setCategory(item.getArticle().getCategory());
        Assertions.assertEquals(this.calculator.calculateDiscount(discount, item).compareTo(BigDecimal.valueOf(0.21)), 0);
    }
}
