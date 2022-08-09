package com.grocery.demo.Evaluator;

import com.grocery.demo.exception.GroceryException;
import com.grocery.demo.model.Discount;
import com.grocery.demo.model.DiscountRuleType;
import com.grocery.demo.model.DiscountType;
import com.grocery.demo.model.OrderItem;
import com.grocery.demo.service.calculator.FreeUnitsCalculator;
import com.grocery.demo.util.OrderTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class FreeUnitsCalculatorTest {

    FreeUnitsCalculator calculator = new FreeUnitsCalculator();

    @Test
    public void calculateDiscountAmount() throws GroceryException {
        OrderItem item = OrderTestUtil.buildOrderItem();
        Discount discount = OrderTestUtil.buildDiscount(DiscountRuleType.STORAGE_TIME);
        discount.setCategory(item.getArticle().getCategory());
        discount.setDiscountType(DiscountType.FREE_UNITS);
        discount.setDiscountUnit(BigDecimal.valueOf(2));
        discount.setMinPurchasedQuantity(3);
        Assertions.assertEquals(this.calculator.calculateDiscount(discount, item).compareTo(BigDecimal.valueOf(2)), 0);
    }

}
