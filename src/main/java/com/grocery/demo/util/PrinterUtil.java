package com.grocery.demo.util;

import com.grocery.demo.model.Order;
import com.grocery.demo.model.OrderItem;
import org.apache.commons.lang3.StringUtils;

public final class PrinterUtil {

    public static void printOrder(Order order) {
        System.out.println("\n\nReceipt Order No " + order.getId());
        System.out.println(StringUtils.rightPad("-", 64, "-"));
        System.out.println("Quantity   " + "Article       " + " Price  " + "  SubTotal " + "   Discount" + "    Total");
        System.out.println(StringUtils.rightPad("-", 64, "-"));
        for (OrderItem item : order.getItems()) {
            String nameArticle = item.getArticle().getName();
            String nameCategory = item.getArticle().getCategory().getName();
            if (nameArticle.length() > 6) {
                nameArticle = item.getArticle().getName().substring(0, 6);
            }

            if (nameCategory.length() > 4) {
                nameCategory = item.getArticle().getCategory().getName().substring(0, 4);
            }


            String quantity = StringUtils.rightPad(String.valueOf(item.getQuantity()), 10);
            String nameItem = StringUtils.rightPad(nameArticle + "-" + nameCategory, 14);
            String unitPrice = StringUtils.rightPad(String.format("€%.2f", item.getArticle().getUnitPrice()), 10);
            String subTotal = StringUtils.rightPad(String.format("€%.2f", item.getSubTotalAmount()), 10);
            String discount = StringUtils.rightPad(String.format("€%.2f", item.getAppliedDiscount()), 10);
            String total = StringUtils.rightPad(String.format("€%.2f", item.getTotalAmount()), 10);
            System.out.format("%s" + " %s" + " %s" + " %s" + " %s" + " %s%n", quantity,
                    nameItem, unitPrice, subTotal, discount, total);
        }

        System.out.println(StringUtils.rightPad("-", 64, "-"));
        System.out.format("%s" + "      €%.2f%n", "Sub-Total", order.getSubTotalAmount());
        System.out.format("%s" + "      €%.2f%n", "Discount ", order.getDiscountAmount());
        System.out.format("%s" + "      €%.2f%n", "Total    ", order.getTotalAmount());

    }
}
