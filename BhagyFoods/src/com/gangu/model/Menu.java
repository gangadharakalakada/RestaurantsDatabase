package com.gangu.model;

public class Menu {
    private int menuId;
    private int restaurantId;
    private String itemName;
    private String description;
    private float price;
    private float rating;
    private String isAvailable;
    private String imagePath;

    public Menu(){

    }
    public Menu(int menuId,int restaurantId,String itemName,String description,float price,float rating,String isAvailable,String imagePath){
        this.menuId=menuId;
        this.restaurantId=restaurantId;
        this.itemName=itemName;
        this.description=description;
        this.price=price;
        this.rating=rating;
        this.isAvailable=isAvailable;
        this.imagePath=imagePath;

    }
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String toString() {
        return String.format("| %-5s | %-5s | %-20s | %-50s | %-6.2f | %-10s | %-20s | %-20s |",
                menuId, restaurantId,itemName, description, price, rating, isAvailable, imagePath);
    }
}
