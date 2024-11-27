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

@WebServlet(name = "categoryServlet", value = "/category-servlet")
public class CategoryServlet extends HttpServlet {
    private CategoryDAO categoryDAO;

    @Override
    public void init() throws ServletException {
        String url = "jdbc:postgresql://localhost:5432/electronics_store?useUnicode=true&characterEncoding=UTF-8";
        String user = "postgres";
        String password = "Qwerty";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            categoryDAO = new CategoryDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize CategoryServlet", e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        try {
            List<Category> categories = categoryDAO.findAll();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/WEB-INF/views/categories.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Failed to retrieve categories", e);
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
                Category category = new Category();
                category.setName(name);
                categoryDAO.save(category);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Category category = new Category();
                category.setId(id);
                categoryDAO.delete(category);
            }
            response.sendRedirect(request.getContextPath() + "/category-servlet");
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) { // SQLState для нарушения уникальности
                request.setAttribute("errorMessage", "Категория с таким именем уже существует. Пожалуйста, используйте другое имя.");
                doGet(request, response); // Перенаправляем на doGet для отображения ошибки
            } else {
                throw new ServletException("Failed to process category action", e);
            }
        }
    }

    public void destroy() {
    }
}