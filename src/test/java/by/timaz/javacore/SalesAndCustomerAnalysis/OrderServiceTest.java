package by.timaz.javacore.SalesAndCustomerAnalysis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    List<Order> orders1;
    List<Order> orders2;
    Customer customer1;
    @BeforeEach
    void setUp() {
                 customer1 = Customer.builder()
                .customerId("1")
                .age(23)
                .city("Minsk")
                .email("abc@gmail.com")
                .name("Pupkin Vasya")
                .registeredAt(LocalDateTime.of(2020, 1, 1, 0, 0))
                .build();
        Customer customer2 = Customer.builder()
                .customerId("2")
                .age(35)
                .city("Warsaw")
                .email("fgfg@gmail.com")
                .name("Bobr Kirill")
                .registeredAt(LocalDateTime.of(2024, 3, 10, 10, 0))
                .build();
        OrderItem oi1 = OrderItem.builder()
                .category(Category.ELECTRONICS)
                .price(600.0)
                .productName("iPhone")
                .quantity(1)
                .build();
        OrderItem oi2 = OrderItem.builder()
                .category(Category.TOYS)
                .price(50.50)
                .productName("Бобр плюшевый")
                .quantity(10)
                .build();
        Order order1 = Order.builder()
                .orderId("1")
                .orderDate(LocalDateTime.now())
                .customer(customer1)
                .items(List.of(oi1))
                .status(OrderStatus.SHIPPED)
                .build();
        Order order2 = Order.builder()
                .orderId("2")
                .orderDate(LocalDateTime.of(2025, 5, 5, 5, 0))
                .customer(customer2)
                .items(List.of(oi1, oi2))
                .status(OrderStatus.DELIVERED)
                .build();
        Order order3 = Order.builder()
                .orderId("3")
                .orderDate(LocalDateTime.of(2025, 6, 6, 6, 0))
                .customer(customer2)
                .items(List.of(oi1))
                .status(OrderStatus.CANCELLED)
                .build();
        orders1 = List.of(order1, order2, order3);
        orders2 = List.of();

    }

    @Test
    void uniqueCitiesTest() {
            List<String> result = List.of("Minsk","Warsaw");
            assertEquals(result,OrderService.uniqueCities(orders1));
    }
    @Test
    void totalIncomeTest(){
        assertEquals(1705.0,OrderService.totalIncome(orders1));
        assertEquals(0.0,OrderService.totalIncome(orders2));
    }
    @Test
    void averageCheckTest(){
        assertEquals(1105.0,OrderService.averageCheck(orders1));
        assertEquals(0.0,OrderService.averageCheck(orders2));
    }
    @Test
    void frequentCustomersTest(){
        Customer c = Customer.builder().build();
        Order order1 = Order.builder().customer(c).build();
        Order order2 = Order.builder().customer(c).build();
        Order order3 = Order.builder().customer(c).build();
        Order order4 = Order.builder().customer(c).build();
        Order order5 = Order.builder().customer(c).build();
        Order order6 = Order.builder().customer(c).build();
        List<Order> orders = List.of(order1, order2, order3, order4, order5, order6);
        List<Customer> result = List.of(c);
        assertEquals(result,OrderService.frequentCustomers(orders));
        assertEquals(0,OrderService.frequentCustomers(orders2).size());
        assertEquals(0,OrderService.frequentCustomers(orders1).size());

    }
    @Test
    void mostPopularItemsBySaleTest(){
        List<String> result = List.of("Бобр плюшевый");
        assertEquals(result,OrderService.mostPopularItemsBySale(orders1));
    }

    @Test
    void mostPopularItemsBySaleTest2(){
        OrderItem orderItem = OrderItem.builder()
                .category(Category.CLOTHING)
                .price(50.50)
                .productName("Носки1")
                .quantity(10)
                .build();
        OrderItem orderItem2 = OrderItem.builder()
                .category(Category.CLOTHING)
                .price(50.50)
                .productName("Носки2")
                .quantity(10)
                .build();
        Order order = Order.builder()
                .orderId("4")
                .orderDate(LocalDateTime.of(2025, 6, 6, 6, 0))
                .customer(customer1)
                .items(List.of(orderItem, orderItem2))
                .status(OrderStatus.DELIVERED)
                .build();
        List<Order> orders = List.of(order);
        List<String> result = List.of("Носки1", "Носки2");
        assertEquals(result,OrderService.mostPopularItemsBySale(orders));
    }

}