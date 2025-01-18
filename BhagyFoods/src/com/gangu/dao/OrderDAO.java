package com.gangu.dao;

import com.gangu.model.Order;
import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    Order getOrderById(int orderId);
    void updateOrder(Order order);
    void deleteOrder(int orderId);
    List<Order> getAllOrders();
}

