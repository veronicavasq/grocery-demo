package com.grocery.demo.service.evaluator;

import com.grocery.demo.exception.ErrorCode;
import com.grocery.demo.exception.InvalidDiscountRuleException;
import com.grocery.demo.model.DiscountRuleType;

import java.util.Map;

public final class DiscountEvaluatorFactory {

    public DiscountEvaluatorFactory() {
    }

    private final static Map<DiscountRuleType, IRuleEvaluator> evaluatorMap = Map.of(
                DiscountRuleType.STORAGE_TIME, new StorageTimeEvaluator(),
                DiscountRuleType.PURCHASE_QUANTITY_IN_RANGE, new PurchaseQuantityEvaluator()
        );

        public static IRuleEvaluator getRuleEvaluator(DiscountRuleType ruleType) throws InvalidDiscountRuleException {
            if (!evaluatorMap.containsKey(ruleType)) {
                throw new InvalidDiscountRuleException(ErrorCode.UNSUPPORTED_RULE_TYPE, "Unsupported discount rule type");
            }

            return evaluatorMap.get(ruleType);
        }

    }
