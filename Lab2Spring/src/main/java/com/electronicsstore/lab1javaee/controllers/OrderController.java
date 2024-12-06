package com.electronicsstore.lab1javaee.controllers;

import com.electronicsstore.lab1javaee.repository.OrderRepository;
import com.electronicsstore.lab1javaee.tables.Order;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping
    public String addOrder(@RequestParam int customerId, @RequestParam int productId, @RequestParam Date orderDate, @RequestParam int quantity) {
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setProductId(productId);
        order.setOrderDate(orderDate);
        order.setQuantity(quantity);
        orderRepository.save(order);
        return "redirect:/orders";
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam int id) {
        orderRepository.deleteById(id);
        return "redirect:/orders";
    }

    @PostMapping("/update")
    public String updateOrder(@RequestParam int id, @RequestParam int customerId, @RequestParam int productId, @RequestParam Date orderDate, @RequestParam int quantity) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setCustomerId(customerId);
            order.setProductId(productId);
            order.setOrderDate(orderDate);
            order.setQuantity(quantity);
            orderRepository.save(order);
        }
        return "redirect:/orders";
    }
}