package com.grocery.demo.model;

import java.math.BigDecimal;

public class Discount {

    private long id;
    private Category category;
    private String origenCountry;
    private DiscountRule discountRule;
    private BigDecimal discountUnit;
    private DiscountType discountType;
    private float minPurchasedQuantity;

    public Discount() {
    }

    public Discount(long id, Category category, String origenCountry, DiscountRule discountRule, BigDecimal discountUnit, DiscountType discountType, float minPurchasedQuantity) {
        this.id = id;
        this.category = category;
        this.origenCountry = origenCountry;
        this.discountRule = discountRule;
        this.discountUnit = discountUnit;
        this.discountType = discountType;
        this.minPurchasedQuantity = minPurchasedQuantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOrigenCountry() {
        return origenCountry;
    }

    public void setOrigenCountry(String origenCountry) {
        this.origenCountry = origenCountry;
    }

    public DiscountRule getDiscountRule() {
        return discountRule;
    }

    public void setDiscountRule(DiscountRule discountRule) {
        this.discountRule = discountRule;
    }

    public BigDecimal getDiscountUnit() {
        return discountUnit;
    }

    public void setDiscountUnit(BigDecimal discountUnit) {
        this.discountUnit = discountUnit;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public float getMinPurchasedQuantity() {
        return minPurchasedQuantity;
    }

    public void setMinPurchasedQuantity(float minPurchasedQuantity) {
        this.minPurchasedQuantity = minPurchasedQuantity;
    }
}
