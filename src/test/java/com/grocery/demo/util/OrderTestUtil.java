package com.grocery.demo.util;

import com.grocery.demo.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public final class OrderTestUtil {

    public static List<Discount> buildDiscountList() throws IOException {
        return Arrays.asList(OrderUtil.readJson("src/test/resources/discounts.json", Discount[].class));
    }

    public static List<Order> buildOrderList() throws IOException {
        return Arrays.asList(OrderUtil.readJson("src/test/resources/orders.json", Order[].class));
    }

    public static Order buildOrderWithBreadDiscountItems() throws IOException {
        List<Order> orders = Arrays.asList(OrderUtil.readJson("src/test/resources/orders.json", Order[].class));
        Order order = orders.get(0);
        LocalDateTime today = LocalDateTime.now();
        List<OrderItem> breadItems = order.getItems().stream()
                .filter(item -> item.getArticle().getCategory().getId() == 1)
                .map(item -> {
                    if (item.getArticle() != null)
                        item.getArticle().setLastEntryDate(today.minusDays(4));
                    return item;
                }).toList();

        order.setItems(breadItems);
        return order;
    }

    public static Order buildOrderWithBeerDiscountItems() throws IOException {
        List<Order> orders = Arrays.asList(OrderUtil.readJson("src/test/resources/orders.json", Order[].class));
        Order order = orders.get(0);
        List<OrderItem> breadItems = order.getItems().stream()
                .filter(item -> item.getArticle().getCategory().getId() == 2)
                .toList();
        order.setItems(breadItems);
        return order;
    }

    public static Order buildOrderWithVegetableDiscountItems() throws IOException {
        List<Order> orders = Arrays.asList(OrderUtil.readJson("src/test/resources/orders.json", Order[].class));
        Order order = orders.get(0);
        List<OrderItem> breadItems = order.getItems().stream()
                .filter(item -> item.getArticle().getCategory().getId() == 3)
                .toList();
        order.setItems(breadItems);
        return order;
    }


    public static DiscountRule buildRule(DiscountRuleType ruleType) {
        return new DiscountRule(3, 0.0001f, 100, ruleType);
    }

    public static Discount buildDiscount(DiscountRuleType ruleType) {
        Category vegetablesCategory = new Category(3, "Vegetables");
        return new Discount(3, vegetablesCategory, null, new DiscountRule(3, 1, 100, ruleType), BigDecimal.valueOf(7), DiscountType.PERCENTAGE, 1);
    }

    public static OrderItem buildOrderItem() {
        Category breadCategory = new Category(1, "Bread");
        Article article1 = new Article(1, "French Bread", breadCategory, null, new BigDecimal("1.00"), LocalDateTime.now().minusDays(1));
        return new OrderItem(article1, 3);
    }

    public static Discount buildDiscount(DiscountType type) {
        Category vegetablesCategory = new Category(3, "Vegetables");
        return new Discount(3, vegetablesCategory, null, new DiscountRule(3, 1, 100, DiscountRuleType.PURCHASE_QUANTITY_IN_RANGE), BigDecimal.valueOf(7), type, 1);
    }

    public static List<Order> buildOrderListWithDiscounts() throws IOException {
        List<Order> ordersToProcess = buildOrderList();
        ordersToProcess.forEach(order -> order.getItems()
                .forEach(item -> {
                    if (item.getArticle() != null && item.getArticle().getCategory().getId() == 1) {
                        item.getArticle().setLastEntryDate(LocalDateTime.now().minusDays(item.getArticle().getId() + 2));
                    }
                })
        );
        return ordersToProcess;
    }
}
