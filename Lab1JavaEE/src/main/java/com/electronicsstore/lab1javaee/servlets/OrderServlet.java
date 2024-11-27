package com.electronicsstore.lab1javaee.servlets;
import java.io.*;
import com.electronicsstore.lab1javaee.DAO.*;
import com.electronicsstore.lab1javaee.tables.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "orderServlet", value = "/order-servlet")
public class OrderServlet extends HttpServlet {
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        String url = "jdbc:postgresql://localhost:5432/electronics_store?useUnicode=true&characterEncoding=UTF-8";
        String user = "postgres";
        String password = "Qwerty";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            orderDAO = new OrderDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize OrderServlet", e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        try {
            List<Order> orders = orderDAO.findAll();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Failed to retrieve orders", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                int customerId = Integer.parseInt(request.getParameter("customerId"));
                int productId = Integer.parseInt(request.getParameter("productId"));
                Date orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("orderDate"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                Order order = new Order();
                order.setCustomerId(customerId);
                order.setProductId(productId);
                order.setOrderDate(orderDate);
                order.setQuantity(quantity);
                orderDAO.save(order);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                orderDAO.delete(id);
            }
            response.sendRedirect(request.getContextPath() + "/order-servlet");
        } catch (SQLException | ParseException e) {
            throw new ServletException("Failed to process order action", e);
        }
    }

    public void destroy() {
    }
}