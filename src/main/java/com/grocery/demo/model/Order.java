package com.grocery.demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private long id;
    private List<OrderItem> items;
    private BigDecimal subTotalAmount;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;

    public Order() {
        this.items=new ArrayList<>();
        this.subTotalAmount=BigDecimal.ZERO;
        this.discountAmount=BigDecimal.ZERO;
        this.totalAmount=BigDecimal.ZERO;
    }

    public Order(long id, List<OrderItem> items, BigDecimal subTotalAmount, BigDecimal discountAmount, BigDecimal totalAmount) {
        this.id = id;
        this.items = items;
        this.subTotalAmount = subTotalAmount;
        this.discountAmount = discountAmount;
        this.totalAmount = totalAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(BigDecimal subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void addItem(OrderItem item){
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items=" + items +
                ", subTotalAmount=" + subTotalAmount.setScale(2, RoundingMode.HALF_UP) +
                ", discountAmount=" + discountAmount.setScale(2, RoundingMode.HALF_UP) +
                ", totalAmount=" + totalAmount.setScale(2, RoundingMode.HALF_UP) +
                '}';
    }
}
