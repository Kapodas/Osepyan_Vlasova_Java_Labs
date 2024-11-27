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

@WebServlet(name = "productServlet", value = "/product-servlet")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        String url = "jdbc:postgresql://localhost:5432/electronics_store?useUnicode=true&characterEncoding=UTF-8";
        String user = "postgres";
        String password = "Qwerty";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            productDAO = new ProductDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize ProductServlet", e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        try {
            List<Product> products = productDAO.findAll();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Failed to retrieve products", e);
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
                double price = Double.parseDouble(request.getParameter("price"));
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));

                Product product = new Product();
                product.setName(name);
                product.setPrice(price);
                product.setCategoryId(categoryId);
                productDAO.save(product);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                productDAO.delete(id);
            }
            response.sendRedirect(request.getContextPath() + "/product-servlet");
        } catch (SQLException e) {
            throw new ServletException("Failed to process product action", e);
        }
    }

    public void destroy() {
    }
}