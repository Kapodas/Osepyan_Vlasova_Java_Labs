package com.electronicsstore.lab1javaee.servlets;

import com.electronicsstore.lab1javaee.ejb.CategoryService;
import com.electronicsstore.lab1javaee.entities.Category;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "categoryServlet", value = "/category-servlet")
public class CategoryServlet extends HttpServlet {
    @EJB
    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/views/categories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            Category category = new Category();
            category.setName(name);
            categoryService.save(category);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            categoryService.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/category-servlet");
    }
}