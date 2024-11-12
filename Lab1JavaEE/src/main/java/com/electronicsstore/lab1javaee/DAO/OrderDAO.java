package com.electronicsstore.lab1javaee.DAO;

import com.electronicsstore.lab1javaee.tables.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    // Метод для сохранения заказа
    public void save(Order order) throws SQLException {
        String sql = "INSERT INTO orders (customer_id, product_id, order_date, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setInt(2, order.getProductId());
            stmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setInt(4, order.getQuantity());
            stmt.executeUpdate();
        }
    }

    // Метод для обновления заказа
    public void update(Order order) throws SQLException {
        String sql = "UPDATE orders SET customer_id = ?, product_id = ?, order_date = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setInt(2, order.getProductId());
            stmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setInt(4, order.getQuantity());
            stmt.setInt(5, order.getId());
            stmt.executeUpdate();
        }
    }

    // Метод для удаления заказа по ID
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Метод для поиска заказа по ID
    public Order findById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setCustomerId(rs.getInt("customer_id"));
                    order.setProductId(rs.getInt("product_id"));
                    order.setOrderDate(rs.getDate("order_date"));
                    order.setQuantity(rs.getInt("quantity"));
                    return order;
                }
            }
        }
        return null;
    }

    // Метод для получения всех заказов
    public List<Order> findAll() throws SQLException {
        String sql = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setProductId(rs.getInt("product_id"));
                order.setOrderDate(rs.getDate("order_date"));
                order.setQuantity(rs.getInt("quantity"));
                orders.add(order);
            }
        }
        return orders;
    }
}