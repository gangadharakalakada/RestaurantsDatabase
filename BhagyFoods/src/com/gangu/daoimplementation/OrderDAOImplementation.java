package com.gangu.daoimplementation;

import com.gangu.dao.OrderDAO;
import com.gangu.model.Order;
import com.UtilityClass.DBConnections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImplementation implements OrderDAO {
    private static final String INSERT_SQL = "INSERT INTO orders (orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM orders WHERE orderId = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM orders";
    private static final String UPDATE_SQL = "UPDATE orders SET userId = ?, restaurantId = ?, orderDate = ?, totalAmount = ?, status = ?, paymentMode = ? WHERE orderId = ?";
    private static final String DELETE_SQL = "DELETE FROM orders WHERE orderId = ?";

    @Override
    public void addOrder(Order order) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setInt(3, order.getRestaurantId());
            preparedStatement.setString(4, order.getOrderDate());
            preparedStatement.setFloat(5, order.getTotalAmount());
            preparedStatement.setString(6, order.getStatus());
            preparedStatement.setString(7, order.getPaymentMode());
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = extractOrder(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getRestaurantId());
            preparedStatement.setString(3, order.getOrderDate());
            preparedStatement.setFloat(4, order.getTotalAmount());
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setString(6, order.getPaymentMode());
            preparedStatement.setInt(7, order.getOrderId());
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println(rowsUpdated + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, orderId);
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println(rowsDeleted + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DBConnections.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL)) {
            while (resultSet.next()) {
                Order order = extractOrder(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Order extractOrder(ResultSet resultSet) throws SQLException {
        int orderId = resultSet.getInt("orderId");
        int userId = resultSet.getInt("userId");
        int restaurantId = resultSet.getInt("restaurantId");
        String orderDate = resultSet.getString("orderDate");
        float totalAmount = resultSet.getFloat("totalAmount");
        String status = resultSet.getString("status");
        String paymentMode = resultSet.getString("paymentMode");

        return new Order(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMode);
    }

    public static void main(String[] args) {
        OrderDAOImplementation orderDAO = new OrderDAOImplementation();

        // Example: Add a new Order
        Order newOrder = new Order();
        newOrder.setOrderId(11);
        newOrder.setUserId(1);
        newOrder.setRestaurantId(5);
        newOrder.setOrderDate("2023-12-27");
        newOrder.setTotalAmount(59.99f);
        newOrder.setStatus("Pending");
        newOrder.setPaymentMode("Credit Card");
        orderDAO.addOrder(newOrder);

        // Example: Fetch and print all Orders
        List<Order> orders = orderDAO.getAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}

