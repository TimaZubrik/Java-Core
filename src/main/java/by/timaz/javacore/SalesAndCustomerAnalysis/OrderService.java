package by.timaz.javacore.SalesAndCustomerAnalysis;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderService {

    public static List<String> uniqueCities(List<Order> orders) {

        return orders.stream().map(order -> order.getCustomer().getCity())
                .distinct().collect(Collectors.toList());
    }

    public static double totalIncome(List<Order> orders) {

        return orders.stream().filter(
                order -> order.getStatus().equals(OrderStatus.DELIVERED)
                || order.getStatus().equals(OrderStatus.CANCELLED))
                .flatMap(order -> order.getItems()
                                .stream()
                                .map(oi -> oi.getPrice()*oi.getQuantity())
                        ).reduce(0.0,Double::sum);
    }

    public static double averageCheck(List<Order> orders) {

        return orders.stream().filter(order -> order.getStatus().equals(OrderStatus.DELIVERED))
                .mapToDouble(order -> order.getItems().stream()
                        .mapToDouble(item -> item.getPrice()*item.getQuantity())
                        .sum()).average().orElse(0.0);
    }

    public static List<Customer> frequentCustomers(List<Order> orders) {
        Map<Customer, Long> ordersCountByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.counting()));

        return ordersCountByCustomer.entrySet().stream()
                .filter(entry -> entry.getValue() > 5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static List<String> mostPopularItemsBySale(List<Order> orders) {

                var items = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(
                        OrderItem::getProductName,
                        Collectors.summingInt(OrderItem::getQuantity)
                ));

                int popularNum = items.values().stream().max(Comparator.naturalOrder()).orElse(0);

            return items.entrySet().stream().filter(entry -> entry.getValue() == popularNum)
                .map(Map.Entry::getKey).sorted().toList();
    }


}
