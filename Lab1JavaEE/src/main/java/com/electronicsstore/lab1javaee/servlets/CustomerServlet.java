package com.electronicsstore.lab1javaee.servlets;

import com.electronicsstore.lab1javaee.ejb.CustomerService;
import com.electronicsstore.lab1javaee.entities.Customer;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "customerServlet", value = "/customer-servlet")
public class CustomerServlet extends HttpServlet {
    @EJB
    private CustomerService customerService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerService.findAll();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/customers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");

            Customer customer = new Customer();
            customer.setName(name);
            customer.setPhoneNumber(phoneNumber);
            customer.setEmail(email);

            customerService.save(customer);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            customerService.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/customer-servlet");
    }
}