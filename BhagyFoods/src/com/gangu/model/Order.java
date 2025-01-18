package com.gangu.model;

public class Order {
    private int orderId;
    private int userId;
    private int restaurantId;
    private String orderDate;
    private float totalAmount;
    private String status;
    private String paymentMode;
    public Order(){

    }
    public Order(int orderId,int userId,int restaurantId,String orderDate,float totalAmount,String status,String paymentMode){
        this.orderId=orderId;
        this.userId=userId;
        this.restaurantId=restaurantId;
        this.orderDate=orderDate;
        this.totalAmount=totalAmount;
        this.status=status;
        this.paymentMode=paymentMode;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    @Override
    public String toString() {
        return String.format(
                "| %-10s | %-10s | %-15s | %-20s | %-15s | %-10s | %-15s |",
                orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMode
        );
    }

}
