package com.grocery.demo;

import com.grocery.demo.exception.GroceryException;
import com.grocery.demo.model.Order;
import com.grocery.demo.repository.DiscountRepository;
import com.grocery.demo.repository.OrderRepository;
import com.grocery.demo.service.OrderService;
import com.grocery.demo.util.OrderTestUtil;
import com.grocery.demo.util.OrderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;


public class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;

    @BeforeEach
    void init() throws IOException {
        DiscountRepository discountRepository = Mockito.mock(DiscountRepository.class);
        this.orderRepository = Mockito.mock(OrderRepository.class);
        this.orderService = new OrderService(discountRepository, orderRepository);
        Mockito.when(discountRepository.findAll()).thenReturn(OrderTestUtil.buildDiscountList());
    }

    @Test
    public void processOrderWithBreadDiscountTest() throws IOException, GroceryException {
        Order order = OrderTestUtil.buildOrderWithBreadDiscountItems();
        Order processed = this.orderService.processOrder(order);
        Assertions.assertEquals(processed.getDiscountAmount().compareTo(BigDecimal.valueOf(6.75)), 0);
        Assertions.assertEquals(processed.getSubTotalAmount().compareTo(BigDecimal.valueOf(15.75)), 0);
        Assertions.assertEquals(processed.getTotalAmount().compareTo(BigDecimal.valueOf(9)), 0);
    }

    @Test
    public void processOrderWithBeerDiscountTest() throws IOException, GroceryException {
        Order order = OrderTestUtil.buildOrderWithBeerDiscountItems();
        Order processed = this.orderService.processOrder(order);
        Assertions.assertEquals(processed.getDiscountAmount().compareTo(BigDecimal.valueOf(8)), 0);
        Assertions.assertEquals(processed.getSubTotalAmount().compareTo(BigDecimal.valueOf(57.15)), 0);
        Assertions.assertEquals(processed.getTotalAmount().compareTo(BigDecimal.valueOf(49.15)), 0);
    }

    @Test
    public void processOrderWithVegetablesDiscountTest() throws IOException, GroceryException {
        Order order = OrderTestUtil.buildOrderWithVegetableDiscountItems();
        Order processed = this.orderService.processOrder(order);
        Assertions.assertEquals(processed.getDiscountAmount().setScale(4, RoundingMode.HALF_UP).compareTo(BigDecimal.valueOf(2.4030)), 0);
        Assertions.assertEquals(processed.getSubTotalAmount().setScale(4, RoundingMode.HALF_UP).compareTo(BigDecimal.valueOf(27.2569)), 0);
        Assertions.assertEquals(processed.getTotalAmount().setScale(4, RoundingMode.HALF_UP).compareTo(BigDecimal.valueOf(24.8539)), 0);

    }

    @Test
    public void processOrderList() throws IOException, GroceryException {
        List<Order> orderList = Arrays
                .asList(OrderUtil.readJson("src/test/resources/processed-orders.json", Order[].class));

        Mockito.when(orderRepository.findAll()).thenReturn(OrderTestUtil.buildOrderListWithDiscounts());

        List<Order> processedTestList = this.orderService.processOrderList();

        Assertions.assertEquals(processedTestList.size(), 3);
        for (int i = 0; i < processedTestList.size(); i++) {
            Assertions.assertEquals(processedTestList.get(i).getDiscountAmount().setScale(4, RoundingMode.HALF_UP).compareTo(orderList.get(i).getDiscountAmount().setScale(4, RoundingMode.HALF_UP)), 0);
            Assertions.assertEquals(processedTestList.get(i).getSubTotalAmount().setScale(4, RoundingMode.HALF_UP).compareTo(orderList.get(i).getSubTotalAmount().setScale(4, RoundingMode.HALF_UP)), 0);
            Assertions.assertEquals(processedTestList.get(i).getTotalAmount().setScale(4, RoundingMode.HALF_UP).compareTo(orderList.get(i).getTotalAmount().setScale(4, RoundingMode.HALF_UP)), 0);

        }
    }
}
