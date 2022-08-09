package com.grocery.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.grocery.demo.model.Discount;
import com.grocery.demo.model.Order;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public final class OrderUtil {

    public static ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    public static <T> T readJson(String filePath, Class<T> clazz) throws IOException {
        ObjectMapper mapper = buildObjectMapper();
        File file = new File(filePath);
        String data = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return mapper.readValue(data, clazz);
    }

    public static List<Discount> buildDiscountList() throws IOException {
        return Arrays.asList(readJson("src/main/resources/discounts.json", Discount[].class));
    }

    public static List<Order> buildOrderList() throws IOException {
        return Arrays.asList(readJson("src/main/resources/orders.json", Order[].class));
    }

}
