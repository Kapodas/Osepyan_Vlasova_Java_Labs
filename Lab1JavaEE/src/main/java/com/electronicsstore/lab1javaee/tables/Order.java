package com.electronicsstore.lab1javaee.tables;
import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int id;
    private int customerId;
    private int productId;
    private Date orderDate;
    private int quantity;

    // Конструктор по умолчанию
    public Order() {}

    // Конструктор с параметрами
    public Order(int id, int customerId, int productId, Date orderDate, int quantity) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", orderDate=" + orderDate +
                ", quantity=" + quantity +
                '}';
    }
}