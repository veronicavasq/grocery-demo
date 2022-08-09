package com.grocery.demo.Evaluator;

import com.grocery.demo.exception.GroceryException;
import com.grocery.demo.model.DiscountRuleType;
import com.grocery.demo.service.evaluator.DiscountEvaluatorFactory;
import com.grocery.demo.service.evaluator.IRuleEvaluator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiscountEvaluatorFactoryTest {

    @Test
    public void getEvaluatorForEveryRuleType() throws GroceryException {
        for (DiscountRuleType discountRuleType : DiscountRuleType.values()) {
            IRuleEvaluator processor = DiscountEvaluatorFactory.getRuleEvaluator(discountRuleType);
            Assertions.assertNotNull(processor);
        }
    }
}
