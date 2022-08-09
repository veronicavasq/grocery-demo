package com.grocery.demo;

import com.grocery.demo.model.Order;
import com.grocery.demo.repository.DiscountRepository;
import com.grocery.demo.repository.OrderRepository;
import com.grocery.demo.service.OrderService;
import com.grocery.demo.util.PrinterUtil;

import java.util.List;

public class DemoApplication {

    public static void main(String[] args) {
        try {
            OrderService orderService = new OrderService(DiscountRepository.getInstance(), OrderRepository.getInstance());
            List<Order> orderList = orderService.processOrderList();
            orderList.forEach(PrinterUtil::printOrder);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}