package com.grocery.demo.model;

public class DiscountRule {

    private long id;
    private Float minimum;
    private Float maximum;
    private DiscountRuleType type;

    public DiscountRule() {
    }

    public DiscountRule(long id, float minimum, float maximum, DiscountRuleType type) {
        this.id = id;
        this.minimum = minimum;
        this.maximum = maximum;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getMinimum() {
        return minimum;
    }

    public void setMinimum(Float minimum) {
        this.minimum = minimum;
    }

    public Float getMaximum() {
        return maximum;
    }

    public void setMaximum(Float maximum) {
        this.maximum = maximum;
    }

    public DiscountRuleType getType() {
        return type;
    }

    public void setType(DiscountRuleType type) {
        this.type = type;
    }
}
