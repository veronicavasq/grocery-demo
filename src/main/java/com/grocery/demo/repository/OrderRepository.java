package com.grocery.demo.repository;

import com.grocery.demo.model.Order;
import com.grocery.demo.service.OrderService;
import com.grocery.demo.util.OrderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private static OrderRepository INSTANCE;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderRepository() {
    }

    public static OrderRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderRepository();
        }
        return INSTANCE;
    }

    public List<Order> findAll() {
        try {
            return OrderUtil.buildOrderList();
        } catch (IOException exception) {
            exception.printStackTrace();
            logger.warn("Error to load order list from json file");
        }
        return new ArrayList<>();
    }
}
