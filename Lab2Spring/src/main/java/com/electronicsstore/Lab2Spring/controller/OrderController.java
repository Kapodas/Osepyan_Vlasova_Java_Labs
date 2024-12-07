package com.electronicsstore.Lab2Spring.controller;

import com.electronicsstore.Lab2Spring.repository.OrderRepository;
import com.electronicsstore.Lab2Spring.repository.CustomerRepository;
import com.electronicsstore.Lab2Spring.repository.ProductRepository;
import com.electronicsstore.Lab2Spring.table.Order;
import com.electronicsstore.Lab2Spring.table.Customer;
import com.electronicsstore.Lab2Spring.table.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping
    public String addOrder(@RequestParam int customerId, @RequestParam int productId,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate,
                           @RequestParam int quantity) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        if (customer != null && product != null) {
            Order order = new Order();
            order.setCustomer(customer);
            order.setProduct(product);
            order.setOrderDate(orderDate);
            order.setQuantity(quantity);
            orderRepository.save(order);
        }
        return "redirect:/orders";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable int id) {
        orderRepository.deleteById(id);
        return "redirect:/orders";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable int id, @RequestParam int customerId, @RequestParam int productId,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate,
                              @RequestParam int quantity) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            Customer customer = customerRepository.findById(customerId).orElse(null);
            Product product = productRepository.findById(productId).orElse(null);
            if (customer != null && product != null) {
                order.setCustomer(customer);
                order.setProduct(product);
                order.setOrderDate(orderDate);
                order.setQuantity(quantity);
                orderRepository.save(order);
            }
        }
        return "redirect:/orders";
    }
}