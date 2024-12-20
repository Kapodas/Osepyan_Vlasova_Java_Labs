package com.electronicsstore.lab1javaee.tables;
import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private double price;
    private int categoryId;
    private String categoryName;

    public Product() {}

    public Product(int id, String name, double price, int categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}