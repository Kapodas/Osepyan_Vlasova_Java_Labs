package com.electronicsstore.lab1javaee.DAO;

import com.electronicsstore.lab1javaee.tables.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    // Метод для сохранения продукта
    public void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, category_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getCategoryId());
            stmt.executeUpdate();
        }
    }

    // Метод для обновления продукта
    public void update(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, category_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getCategoryId());
            stmt.setInt(4, product.getId());
            stmt.executeUpdate();
        }
    }

    // Метод для удаления продукта по ID
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Метод для поиска продукта по ID
    public Product findById(int id) throws SQLException {
        String sql = "SELECT p.id, p.name, p.price, p.category_id, c.name AS category_name " +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.id " +
                "WHERE p.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setCategoryName(rs.getString("category_name"));
                    return product;
                }
            }
        }
        return null;
    }

    // Метод для получения всех продуктов
    public List<Product> findAll() throws SQLException {
        String sql = "SELECT p.id, p.name, p.price, p.category_id, c.name AS category_name " +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.id";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                products.add(product);
            }
        }
        return products;
    }
}