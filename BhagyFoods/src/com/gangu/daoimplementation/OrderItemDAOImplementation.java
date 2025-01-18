package com.gangu.daoimplementation;

import com.gangu.dao.OrderItemDAO;
import com.gangu.model.OrderItem;
import com.UtilityClass.DBConnections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImplementation implements OrderItemDAO {
    private static final String INSERT_SQL = "INSERT INTO ordersitem (orderItemId, orderId, menuId, quantity, totalPrice) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM ordersitem WHERE orderItemId = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM ordersitem";
    private static final String UPDATE_SQL = "UPDATE ordersitem SET orderId = ?, menuId = ?, quantity = ?, totalPrice = ? WHERE orderItemId = ?";
    private static final String DELETE_SQL = "DELETE FROM ordersitem WHERE orderItemId = ?";

    @Override
    public void addOrderItem(OrderItem orderItem) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setInt(1, orderItem.getOrderItemId());
            preparedStatement.setInt(2, orderItem.getOrderId());
            preparedStatement.setInt(3, orderItem.getMenuId());
            preparedStatement.setString(4, orderItem.getQuantity());
            preparedStatement.setFloat(5, orderItem.getTotalPrice());
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {
        OrderItem orderItem = null;
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setInt(1, orderItemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                orderItem = extractOrderItem(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getMenuId());
            preparedStatement.setString(3, orderItem.getQuantity());
            preparedStatement.setFloat(4, orderItem.getTotalPrice());
            preparedStatement.setInt(5, orderItem.getOrderItemId());
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println(rowsUpdated + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, orderItemId);
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println(rowsDeleted + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection connection = DBConnections.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL)) {
            while (resultSet.next()) {
                OrderItem orderItem = extractOrderItem(resultSet);
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    private OrderItem extractOrderItem(ResultSet resultSet) throws SQLException {
        int orderItemId = resultSet.getInt("orderItemId");
        int orderId = resultSet.getInt("orderId");
        int menuId = resultSet.getInt("menuId");
        String quantity = resultSet.getString("quantity");
        float totalPrice = resultSet.getFloat("totalPrice");

        return new OrderItem(orderItemId, orderId, menuId, quantity, totalPrice);
    }

    public static void main(String[] args) {
        OrderItemDAOImplementation orderItemDAO = new OrderItemDAOImplementation();

        // Example: Add a new OrderItem
        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setOrderItemId(11);
        newOrderItem.setOrderId(1);
        newOrderItem.setMenuId(5);
        newOrderItem.setQuantity("2");
        newOrderItem.setTotalPrice(19.99f);
        orderItemDAO.addOrderItem(newOrderItem);

        // Example: Fetch and print all OrderItems
        List<OrderItem> orderItems = orderItemDAO.getAllOrderItems();
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}

