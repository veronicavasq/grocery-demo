package com.grocery.demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article {

    private long id;
    private String name;
    private Category category;
    private String origenCountry;
    private BigDecimal unitPrice;
    private LocalDateTime lastEntryDate;

    public Article() {
    }

    public Article(long id, String name, Category category, String origenCountry, BigDecimal unitPrice, LocalDateTime lastEntryDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.origenCountry = origenCountry;
        this.unitPrice = unitPrice;
        this.lastEntryDate = lastEntryDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDateTime getLastEntryDate() {
        return lastEntryDate;
    }

    public void setLastEntryDate(LocalDateTime lastEntryDate) {
        this.lastEntryDate = lastEntryDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", origenCountry='" + origenCountry + '\'' +
                ", unitPrice=" + unitPrice.setScale(2, RoundingMode.HALF_UP) +
                ", lastEntryDate=" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(lastEntryDate) +
                '}';
    }
}
