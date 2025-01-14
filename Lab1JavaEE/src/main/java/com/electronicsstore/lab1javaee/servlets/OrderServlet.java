package com.electronicsstore.lab1javaee.servlets;

import com.electronicsstore.lab1javaee.ejb.CustomerService;
import com.electronicsstore.lab1javaee.ejb.OrderService;
import com.electronicsstore.lab1javaee.ejb.ProductService;
import com.electronicsstore.lab1javaee.entities.Customer;
import com.electronicsstore.lab1javaee.entities.Order;
import com.electronicsstore.lab1javaee.entities.Product;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "orderServlet", value = "/order-servlet")
public class OrderServlet extends HttpServlet {
    @EJB
    private OrderService orderService;

    @EJB
    private CustomerService customerService;

    @EJB
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.findAll();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                int customerId = Integer.parseInt(request.getParameter("customerId"));
                int productId = Integer.parseInt(request.getParameter("productId"));
                Date orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("orderDate"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                // Получаем Customer и Product по их ID
                Customer customer = customerService.findById(customerId);
                Product product = productService.findById(productId);

                if (customer != null && product != null) {
                    Order order = new Order();
                    order.setCustomer(customer);
                    order.setProduct(product);
                    order.setOrderDate(orderDate);
                    order.setQuantity(quantity);

                    orderService.save(order);
                }
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                orderService.delete(id);
            }
            response.sendRedirect(request.getContextPath() + "/order-servlet");
        } catch (ParseException e) {
            throw new ServletException("Failed to parse order date", e);
        }
    }
}