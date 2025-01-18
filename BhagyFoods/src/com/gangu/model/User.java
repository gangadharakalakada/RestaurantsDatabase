package com.gangu.model;

public class User {
    private int userId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String role;
    private String createdDate;
    private String lastLoginDate;
    public User(){

    }
    public User(int userId,String name,String username,String password,String email,String phone,String address,
                String role,String createdDate,String lastLoginDate){
        this.userId=userId;
        this.name=name;
        this.username=username;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.address=address;
        this.role=role;
        this.createdDate=createdDate;
        this.lastLoginDate=lastLoginDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    public String toString(){
        return String.format("| %-2s | %-20s | %-20s | %-20s | %-30s | %-10s | %-20s | %-10s | %-5s | %-5s |",userId,name,username,
        password,email,phone,address,role,createdDate,lastLoginDate);
    }
}
