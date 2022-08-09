package com.grocery.demo.service;

import com.grocery.demo.exception.ErrorCode;
import com.grocery.demo.exception.GroceryException;
import com.grocery.demo.exception.InvalidOrderException;
import com.grocery.demo.model.Discount;
import com.grocery.demo.model.Order;
import com.grocery.demo.model.OrderItem;
import com.grocery.demo.repository.DiscountRepository;
import com.grocery.demo.repository.OrderRepository;
import com.grocery.demo.service.evaluator.DiscountEvaluatorFactory;
import com.grocery.demo.service.evaluator.IRuleEvaluator;
import com.grocery.demo.service.processor.DiscountCalculatorFactory;
import com.grocery.demo.service.processor.IDiscountCalculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    private final DiscountRepository discountRepository;
    private final OrderRepository orderRepository;

    public OrderService(DiscountRepository discountRepository, OrderRepository orderRepository) {
        this.discountRepository = discountRepository;
        this.orderRepository = orderRepository;
    }

    public Order processOrder(Order order) throws GroceryException {
        this.validateOrder(order);
        List<Discount> discounts = this.discountRepository.findAll();
        BigDecimal orderTotal = BigDecimal.ZERO;
        BigDecimal orderSubTotal = BigDecimal.ZERO;
        BigDecimal orderDiscountTotal = BigDecimal.ZERO;

        for (OrderItem item : order.getItems()) {
            BigDecimal discountAmount = this.calculateDiscount(discounts, item);
            item.setAppliedDiscount(discountAmount);
            item.setSubTotalAmount(item.getArticle().getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            item.setTotalAmount(item.getSubTotalAmount().subtract(item.getAppliedDiscount()));
            orderSubTotal = orderSubTotal.add(item.getSubTotalAmount());
            orderDiscountTotal = orderDiscountTotal.add(item.getAppliedDiscount());
            orderTotal = orderTotal.add(item.getTotalAmount());
        }

        order.setSubTotalAmount(orderSubTotal);
        order.setDiscountAmount(orderDiscountTotal);
        order.setTotalAmount(orderTotal);

        return order;
    }

    private BigDecimal calculateDiscount(List<Discount> discounts, OrderItem item) throws GroceryException {
        BigDecimal discountAmount = BigDecimal.ZERO;
        List<Discount> discountsByCategory = discounts.stream()
                .filter(discount -> discount.getCategory().getId() == item.getArticle().getCategory().getId())
                .collect(Collectors.toList());

        for (Discount discount : discountsByCategory) {
            IRuleEvaluator ruleEvaluator = DiscountEvaluatorFactory.getRuleEvaluator(discount.getDiscountRule().getType());

            if (ruleEvaluator.verifyRule(discount, item)) {
                IDiscountCalculator discountCalculator = DiscountCalculatorFactory.getCalculator(discount.getDiscountType());
                discountAmount = discountAmount.add(discountCalculator.calculateDiscount(discount, item));
            }

        }
        return discountAmount;
    }

    private void validateOrder(Order order) throws InvalidOrderException {
        if (order == null || order.getId() == 0 || order.getItems().isEmpty()) {
            throw new InvalidOrderException(ErrorCode.INVALID_ORDER_EXCEPTION, "Invalid order");
        }
        for (OrderItem item : order.getItems()) {
            this.validateItem(item);
        }
    }

    private void validateItem(OrderItem item) throws InvalidOrderException {
        if (item.getArticle() == null || item.getArticle().getId() == 0 || item.getQuantity() == 0) {
            throw new InvalidOrderException(ErrorCode.INVALID_ORDER_EXCEPTION, "Invalid order");
        }
    }

    public List<Order> processOrderList() throws GroceryException {
        List<Order> orders = this.orderRepository.findAll();
        for (Order order : orders) {
            this.processOrder(order);
        }
        return orders;
    }


}

