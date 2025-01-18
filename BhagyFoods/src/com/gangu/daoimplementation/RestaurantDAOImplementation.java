package com.gangu.daoimplementation;

import com.UtilityClass.DBConnections;
import com.gangu.dao.RestaurantDAO;
import com.gangu.model.Restaurant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAOImplementation implements RestaurantDAO {

    // SQL Queries
    private static final String INSERT_SQL = "INSERT INTO `restaurant`(`restaurantId`, `name`,`address`,`phone`, `rating`, `cusineType`, `isActive`, `eta`, `adminUserId`, `imagePath`) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM `restaurant` WHERE `restaurantId` = ?";
    private static final String SQL_SINGLE_RESTAURANT = "SELECT * FROM `restaurant` WHERE `restaurantId` = ?";
    private static final String SQL_GET_ALL_RESTAURANTS = "SELECT * FROM `restaurant`";
    private static final String SQL_FOR_UPDATE = "UPDATE `restaurant` SET `name` = ?, `phone` = ?, `address` = ?, `rating` = ?, `cusineType` = ?, `isActive` = ?, `eta` = ?, `adminUserId` = ?, `imagePath` = ? WHERE `restaurantId` = ?";

    @Override
    public void addRestaurant(Restaurant restaurant) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setInt(1, restaurant.getRestaurantId());
            preparedStatement.setString(2, restaurant.getName());
            preparedStatement.setString(3, restaurant.getPhone());
            preparedStatement.setString(4, restaurant.getAddress());
            preparedStatement.setFloat(5, restaurant.getRating());
            preparedStatement.setString(6, restaurant.getCusineType());
            preparedStatement.setString(7, restaurant.getIsActive());
            preparedStatement.setString(8, restaurant.getEta());
            preparedStatement.setInt(9, restaurant.getAdminUserId());
            preparedStatement.setString(10, restaurant.getImagePath());
            int result = preparedStatement.executeUpdate();
            System.out.println(result + " restaurant added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {
        Restaurant restaurant = null;
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SINGLE_RESTAURANT)) {
            preparedStatement.setInt(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                restaurant = extractRestaurant(resultSet);
                System.out.println(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_UPDATE)) {
            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getPhone());
            preparedStatement.setString(3, restaurant.getAddress());
            preparedStatement.setFloat(4, restaurant.getRating());
            preparedStatement.setString(5, restaurant.getCusineType());
            preparedStatement.setString(6, restaurant.getIsActive());
            preparedStatement.setString(7, restaurant.getEta());
            preparedStatement.setInt(8, restaurant.getAdminUserId());
            preparedStatement.setString(9, restaurant.getImagePath());
            preparedStatement.setInt(10, restaurant.getRestaurantId());
            int result = preparedStatement.executeUpdate();
            System.out.println(result + " restaurant updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, restaurantId);
            int result = preparedStatement.executeUpdate();
            System.out.println(result + " restaurant deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurantList = new ArrayList<>();
        try (Connection connection = DBConnections.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_RESTAURANTS)) {
            while (resultSet.next()) {
                Restaurant restaurant = extractRestaurant(resultSet);
                restaurantList.add(restaurant);
            }
            System.out.println("_______________________________________________________________________________________________________________________________________________________________________________________");
            for(Restaurant r :restaurantList){
                System.out.println(r);
            }
            System.out.println("________________________________________________________________________________________________________________________________________________________________________________________");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    private Restaurant extractRestaurant(ResultSet resultSet) throws SQLException {
        int restaurantId = resultSet.getInt("restaurantId");
        String name = resultSet.getString("name");
        String phone = resultSet.getString("phone");
        String address = resultSet.getString("address");
        float rating = resultSet.getFloat("rating");
        String cusineType = resultSet.getString("cusineType");
        String isActive = resultSet.getString("isActive");
        String eta = resultSet.getString("eta");
        int adminUserId = resultSet.getInt("adminUserId");
        String imagePath = resultSet.getString("imagePath");

        return new Restaurant(restaurantId, name, phone, address, rating, cusineType, isActive, eta, adminUserId, imagePath);
    }

    public static void main(String[] args) {
        RestaurantDAOImplementation restaurantDAOimpl = new RestaurantDAOImplementation();
        restaurantDAOimpl.getAllRestaurants();
    }
}
