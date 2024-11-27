package com.electronicsstore.lab1javaee.servlets;

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

@WebServlet(name = "customerServlet", value = "/customer-servlet")
public class CustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        String url = "jdbc:postgresql://localhost:5432/electronics_store?useUnicode=true&characterEncoding=UTF-8";
        String user = "postgres";
        String password = "Qwerty";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            customerDAO = new CustomerDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize CustomerServlet", e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        try {
            List<Customer> customers = customerDAO.findAll();
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/WEB-INF/views/customers.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Failed to retrieve customers", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                String name = request.getParameter("name");
                String phoneNumber = request.getParameter("phoneNumber");
                String email = request.getParameter("email");
                Customer customer = new Customer();
                customer.setName(name);
                customer.setPhoneNumber(phoneNumber);
                customer.setEmail(email);
                customerDAO.save(customer);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                customerDAO.delete(id);
            }
            response.sendRedirect(request.getContextPath() + "/customer-servlet");
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public void destroy() {
    }
}