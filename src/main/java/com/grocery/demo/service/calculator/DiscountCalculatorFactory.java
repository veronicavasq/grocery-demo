package com.grocery.demo.service.calculator;

import com.grocery.demo.exception.ErrorCode;
import com.grocery.demo.exception.InvalidDiscountCalculatorException;
import com.grocery.demo.model.DiscountType;

import java.util.Map;

public final class DiscountCalculatorFactory {

    public DiscountCalculatorFactory() {
    }

    private final static Map<DiscountType, IDiscountCalculator> calculatorMap = Map.of(
            DiscountType.FREE_UNITS, new FreeUnitsCalculator(),
            DiscountType.PERCENTAGE, new DiscountPercentageCalculator(),
            DiscountType.OFFER_AMOUNT, new OfferDiscountCalculator()
    );

    public static IDiscountCalculator getCalculator(DiscountType ruleType) throws InvalidDiscountCalculatorException {
        if (!calculatorMap.containsKey(ruleType)) {
            throw new InvalidDiscountCalculatorException(ErrorCode.UNSUPPORTED_CALCULATOR_TYPE, "Unsupported type to calculate the discount");
        }

        return calculatorMap.get(ruleType);
    }

}
