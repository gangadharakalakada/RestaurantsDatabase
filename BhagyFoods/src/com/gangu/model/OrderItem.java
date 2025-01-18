package com.gangu.model;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int menuId;
    private String quantity;
    private float totalPrice;

    public OrderItem(int orderItemId,int orderId,int menuId,String quantity,float totalPrice) {
        this.orderItemId=orderItemId;
        this.orderId=orderId;
        this.menuId = menuId;
        this.quantity=quantity;
        this.totalPrice=totalPrice;
    }
    public OrderItem(){

    }
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Override
    public String toString() {
        return String.format(
                "| %-15s | %-10s | %-10s | %-10s | %-15s |",
                orderItemId, orderId, menuId, quantity, totalPrice
        );
    }

}
