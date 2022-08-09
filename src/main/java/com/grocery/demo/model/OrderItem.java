package com.grocery.demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderItem {

    private Article article;
    private float quantity;
    private BigDecimal subTotalAmount;
    private BigDecimal appliedDiscount;
    private BigDecimal totalAmount;

    public OrderItem() {
        this.subTotalAmount=BigDecimal.ZERO;
        this.appliedDiscount=BigDecimal.ZERO;
        this.totalAmount=BigDecimal.ZERO;
    }

    public OrderItem(Article article, float quantity, BigDecimal subTotalAmount, BigDecimal appliedDiscount, BigDecimal totalAmount) {
        this.article = article;
        this.quantity = quantity;
        this.subTotalAmount = subTotalAmount;
        this.appliedDiscount = appliedDiscount;
        this.totalAmount = totalAmount;
    }

    public OrderItem(Article article, float quantity) {
        this.article = article;
        this.quantity = quantity;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setSubTotalAmount(BigDecimal subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    public void setAppliedDiscount(BigDecimal appliedDiscount) {
        this.appliedDiscount = appliedDiscount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Article getArticle() {
        return article;
    }

    public float getQuantity() {
        return quantity;
    }

    public BigDecimal getSubTotalAmount() {
        return subTotalAmount;
    }

    public BigDecimal getAppliedDiscount() {
        return appliedDiscount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "article=" + article.toString() +
                ", quantity=" + quantity +
                ", subTotalAmount=" + subTotalAmount.setScale(2, RoundingMode.HALF_UP) +
                ", appliedDiscount=" + appliedDiscount.setScale(2, RoundingMode.HALF_UP) +
                ", totalAmount=" + totalAmount.setScale(2, RoundingMode.HALF_UP) +
                '}';
    }
}
