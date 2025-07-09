package by.timaz.javacore.SalesAndCustomerAnalysis;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class OrderItem {
    private String productName;
    private int quantity;
    private double price;
    private Category category;
}
