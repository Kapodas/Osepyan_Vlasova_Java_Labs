package com.electronicsstore.lab1javaee;

import java.io.*;
import com.electronicsstore.lab1javaee.DAO.*;
import com.electronicsstore.lab1javaee.tables.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private CustomerDAO customerDAO;
    @Override
    public void init() throws ServletException {
        String url ="jdbc:postgresql://localhost:5432/electronics_store";
        String user = "postgres";
        String password = "Qwerty";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            customerDAO = new CustomerDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize CustomerServlet", e);
        }
    }
    private String message;

    //public void init() {
    //    message = "Hello World!";
    //}
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Customer> customers = customerDAO.findAll();
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/WEB-INF/views/customers.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Failed to retrieve customers", e);
        }
    }

    public void destroy() {
    }
}