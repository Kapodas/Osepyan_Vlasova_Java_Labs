package com.electronicsstore.lab1javaee.DAO;

import com.electronicsstore.lab1javaee.tables.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    // Метод для сохранения клиента
    public void save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (name, phone_number, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhoneNumber());
            stmt.setString(3, customer.getEmail());
            stmt.executeUpdate();
        }
    }

    // Метод для обновления клиента
    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET name = ?, phone_number = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhoneNumber());
            stmt.setString(3, customer.getEmail());
            stmt.setInt(4, customer.getId());
            stmt.executeUpdate();
        }
    }

    // Метод для удаления клиента по ID
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Метод для поиска клиента по ID
    public Customer findById(int id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setName(rs.getString("name"));
                    customer.setPhoneNumber(rs.getString("phone_number"));
                    customer.setEmail(rs.getString("email"));
                    return customer;
                }
            }
        }
        return null;
    }

    // Метод для получения всех клиентов
    public List<Customer> findAll() throws SQLException {
        String sql = "SELECT * FROM customers";
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }
        }
        return customers;
    }
}