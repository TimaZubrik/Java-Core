package by.timaz.javacore.SalesAndCustomerAnalysis;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Builder
public class Order {
    private String orderId;
    private LocalDateTime orderDate;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;
}
@Getter
@Builder
class OrderItem {
    private String productName;
    private int quantity;
    private double price;
    private Category category;
}
@Getter
@Builder
class Customer {
    private String customerId;
    private String name;
    private String email;
    private LocalDateTime registeredAt;
    private int age;
    private String city;
}

enum OrderStatus {
    NEW, PROCESSING, SHIPPED, DELIVERED, CANCELLED
}

enum Category {
    ELECTRONICS, CLOTHING, BOOKS, HOME, BEAUTY, TOYS
}

