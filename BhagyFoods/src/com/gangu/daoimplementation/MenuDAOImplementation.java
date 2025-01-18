package com.gangu.daoimplementation;

import com.UtilityClass.DBConnections;
import com.gangu.dao.MenuDAO;
import com.gangu.model.Menu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImplementation implements MenuDAO {
    private static final String INSERT_SQL = "INSERT INTO menu (menuId, restaurantId, itemName, description, price, rating, isAvailable, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_SQL = "SELECT * FROM menu WHERE menuId = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM menu";
    private static final String UPDATE_SQL = "UPDATE menu SET itemName = ?, description = ?, price = ?, rating = ?, isAvailable = ?, imagePath = ? WHERE menuId = ?";
    private static final String DELETE_SQL = "DELETE FROM menu WHERE menuId = ?";
    private static final String SELECT_BY_RESTAURANT_SQL = "SELECT * FROM menu WHERE restaurantId = ?";

    // Add a new menu item to the database
    @Override
    public void addMenu(Menu menu) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {

            preparedStatement.setInt(1, menu.getMenuId());
            preparedStatement.setInt(2, menu.getRestaurantId());
            preparedStatement.setString(3, menu.getItemName());
            preparedStatement.setString(4, menu.getDescription());
            preparedStatement.setFloat(5, menu.getPrice());
            preparedStatement.setFloat(6, menu.getRating());
            preparedStatement.setString(7, menu.getIsAvailable());
            preparedStatement.setString(8, menu.getImagePath());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " menu item(s) added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a menu item by its ID
    @Override
    public Menu getMenu(int menuId) {
        Menu menu = null;
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)) {

            preparedStatement.setInt(1, menuId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                menu = extractMenu(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    // Update an existing menu item
    @Override
    public void updateMenu(Menu menu) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            preparedStatement.setString(1, menu.getItemName());
            preparedStatement.setString(2, menu.getDescription());
            preparedStatement.setFloat(3, menu.getPrice());
            preparedStatement.setFloat(4, menu.getRating());
            preparedStatement.setString(5, menu.getIsAvailable());
            preparedStatement.setString(6, menu.getImagePath());
            preparedStatement.setInt(7, menu.getMenuId());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " menu item(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a menu item by its ID
    @Override
    public void deleteMenu(int menuId) {
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setInt(1, menuId);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " menu item(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all menu items from the database
    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menuList = new ArrayList<>();
        try (Connection connection = DBConnections.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL)) {

            while (resultSet.next()) {
                Menu menu = extractMenu(resultSet);
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    // Get menu items by restaurantId
    @Override
    public List<Menu> getMenusByRestaurant(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        try (Connection connection = DBConnections.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_RESTAURANT_SQL)) {

            preparedStatement.setInt(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Menu menu = extractMenu(resultSet);
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    // Helper method to extract a Menu object from ResultSet
    private Menu extractMenu(ResultSet resultSet) throws SQLException {
        int menuId = resultSet.getInt("menuId");
        int restaurantId = resultSet.getInt("restaurantId");
        String itemName = resultSet.getString("itemName");
        String description = resultSet.getString("description");
        float price = resultSet.getFloat("price");
        float rating = resultSet.getFloat("rating");
        String isAvailable = resultSet.getString("isAvailable");
        String imagePath = resultSet.getString("imagePath");

        return new Menu(menuId, restaurantId, itemName, description, price, rating, isAvailable, imagePath);
    }

    public static void main(String[] args) {
        MenuDAO menuDAO = new MenuDAOImplementation();
        Menu newMenu = new Menu(11, 1, "Cheese Pizza", "Delicious cheese pizza with extra toppings", 8.99f, 4.5f, "Available", "/images/cheese_pizza.jpg");
        menuDAO.addMenu(newMenu);

        Menu menu = menuDAO.getMenu(1);
        System.out.println(menu);

        menu.setPrice(9.99f);
        menuDAO.updateMenu(menu);

        menuDAO.deleteMenu(1);

        List<Menu> allMenus = menuDAO.getAllMenus();
        for (Menu m : allMenus) {
            System.out.println(m);
        }

        // Get menus by restaurantId
        List<Menu> restaurantMenus = menuDAO.getMenusByRestaurant(1);
        for (Menu m : restaurantMenus) {
            System.out.println(m);
        }
    }
}
