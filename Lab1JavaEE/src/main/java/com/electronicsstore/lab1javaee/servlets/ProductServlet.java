package com.electronicsstore.lab1javaee.servlets;

import com.electronicsstore.lab1javaee.ejb.CategoryService;
import com.electronicsstore.lab1javaee.ejb.ProductService;
import com.electronicsstore.lab1javaee.entities.Category;
import com.electronicsstore.lab1javaee.entities.Product;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "productServlet", value = "/product-servlet")
public class ProductServlet extends HttpServlet {
    @EJB
    private ProductService productService;

    @EJB
    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            // Получаем Category по ID
            Category category = categoryService.findById(categoryId);

            if (category != null) {
                Product product = new Product();
                product.setName(name);
                product.setPrice(price);
                product.setCategory(category);

                productService.save(product);
            }
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            productService.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/product-servlet");
    }
}