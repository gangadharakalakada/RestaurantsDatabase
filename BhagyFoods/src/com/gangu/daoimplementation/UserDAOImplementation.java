package com.gangu.daoimplementation;
import com.UtilityClass.DBConnections;
import com.gangu.dao.UserDAO;
import com.gangu.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class UserDAOImplementation implements UserDAO {
    private static final String INSERT_SQL="insert into `user`(`userId`,`name`,`username`,`password`,`email`,`phone`,`address`,`role`)" +
                "values(?,?,?,?,?,?,?,?)";
    private static final String DELETE_QUERY = "delete from `user` where `userid` = ?";
    private static final String SQL_SINGLE_USER = "select * from `user` where `userid` = ?";
    private static final String SQL_GET_DETAILS_ALL="Select * from `user`";
    private static final String SQL_FOR_UPDATE = "update user set `name` = ?, `password` = ?, `phone` = ?, `address` = ?, `role` = ? where `userId` = ?";

    @Override
    public void addUser(User user) {
        Scanner s = new Scanner(System.in);
        Connection connection=DBConnections.getConnection();
        PreparedStatement preparedStatement=null;
        try{
            preparedStatement=connection.prepareStatement(INSERT_SQL);
            preparedStatement.setInt(1,user.getUserId());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getUsername());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getEmail());
            preparedStatement.setString(6,user.getPhone());
            preparedStatement.setString(7,user.getAddress());
            preparedStatement.setString(8,user.getRole());
            int x =preparedStatement.executeUpdate();
            System.out.println(x);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                preparedStatement.close();
                connection.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public User getUser(int userId) {
        User user=null;
        try(Connection connection=DBConnections.getConnection();
        PreparedStatement preparedStatement =connection.prepareStatement(SQL_SINGLE_USER);)
        {
            preparedStatement.setInt(1,userId);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = extractUser(resultSet);
                System.out.println(user);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateUser(User user) {
        Connection connection=null;
        PreparedStatement preparedStatement;
        try{
            connection=DBConnections.getConnection();
            preparedStatement=connection.prepareStatement(SQL_FOR_UPDATE);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getPhone());
            preparedStatement.setString(4,user.getAddress());
            preparedStatement.setString(5,user.getRole());
            preparedStatement.setInt(6,user.getUserId());
            int x=preparedStatement.executeUpdate();
            System.out.println(x);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUser(int userId) {
       Connection connection=null;
       PreparedStatement preparedStatement;
       try{
           connection = DBConnections.getConnection();
           preparedStatement=connection.prepareStatement(DELETE_QUERY);
           preparedStatement.setInt(1,userId);
           int x=preparedStatement.executeUpdate();
           System.out.println(x);
       }
       catch (SQLException e){
           e.printStackTrace();
       }
    }

    @Override
    public List<User> getAllUser() {
        Connection connection=null;
        Statement statement;
        ArrayList<User>  userList = new ArrayList<>();
        try{
            connection=DBConnections.getConnection();
            statement=connection.createStatement();
            ResultSet res=statement.executeQuery(SQL_GET_DETAILS_ALL);
            while(res.next()){
                User user=extractUser(res);
                userList.add(user);
            }
            System.out.println("_____________________________________________________________________________________________________________________________________________________________________________");
            for(User u:userList){
                System.out.println(u);
            }
            System.out.println("_____________________________________________________________________________________________________________________________________________________________________________");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return List.of();
    }
    public User extractUser(ResultSet res) throws SQLException{
        int userId = res.getInt("userId");
        String name = res.getString("name");
        String username = res.getString("username");
        String password = res.getString("password");
        String email = res.getString("email");
        String phone = res.getString("phone");
        String address = res.getString("address");
        String role = res.getString("role");
        User user=new User(userId,name,username,password,email,phone,address,role,null,null);
        return user;
    }
    public static void main(String[] args){
        UserDAOImplementation userDAOimpl = new UserDAOImplementation();
        User update =new User();
        update.setUserId(12);
        update.setName("Gangu");
        update.setAddress("Banglore");
        update.setPassword("Kalakadag@gmail.com");
        update.setPhone("9885472468");
        update.setRole("Software Developer");
        userDAOimpl.updateUser(update);
    }
}
