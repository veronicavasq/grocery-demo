package com.grocery.demo.calculator;

import com.grocery.demo.exception.GroceryException;
import com.grocery.demo.model.DiscountType;
import com.grocery.demo.service.calculator.DiscountCalculatorFactory;
import com.grocery.demo.service.calculator.IDiscountCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiscountCalculatorFactoryTest {

    @Test
    public void getCalculatorForEveryDiscountType() throws GroceryException {
        for (DiscountType discountType : DiscountType.values()) {
            IDiscountCalculator processor = DiscountCalculatorFactory.getCalculator(discountType);
            Assertions.assertNotNull(processor);
        }
    }
}
