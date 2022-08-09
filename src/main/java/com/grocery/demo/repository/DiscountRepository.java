package com.grocery.demo.repository;

import com.grocery.demo.model.Discount;
import com.grocery.demo.service.OrderService;
import com.grocery.demo.util.OrderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepository {

    private static DiscountRepository INSTANCE;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public DiscountRepository() {
    }

    public static DiscountRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiscountRepository();
        }
        return INSTANCE;
    }

    public List<Discount> findAll(){
        try {
            return OrderUtil.buildDiscountList();
        } catch (IOException exception) {
            exception.printStackTrace();
            logger.warn("Error to load discount list from json file");
        }
        return new ArrayList<>();
    }

}
