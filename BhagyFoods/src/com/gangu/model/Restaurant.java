package com.gangu.model;
public class Restaurant {
    private int restaurantId;
    private String name;
    private String address;
    private String phone;
    private float rating;
    private String cusineType;
    private String isActive;
    private String eta;
    private int adminUserId;
    private String imagePath;
    public Restaurant(){

    }
    public Restaurant(int restaurantId,String name,String address,String phone,float rating,String cusineType,String isActive,String eta
    ,int adminUserId,String imagePath){
        this.restaurantId=restaurantId;
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.rating=rating;
        this.cusineType=cusineType;
        this.isActive=isActive;
        this.eta=eta;
        this.adminUserId=adminUserId;
        this.imagePath=imagePath;
    }
    public String getCusineType() {
        return cusineType;
    }

    public void setCusineType(String cusineType) {
        this.cusineType = cusineType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public int getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(int adminUserId) {
        this.adminUserId = adminUserId;
    }
    public String toString(){
        return String.format("| %-2s | %-20s | %-20s | %-20s | %-5s | %-20s | %-20s | %-10s | %-5s | %-30s |",restaurantId,name,address,phone,rating,cusineType,isActive,eta,adminUserId,imagePath);
    }
}
