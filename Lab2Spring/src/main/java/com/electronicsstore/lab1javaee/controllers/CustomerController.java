package com.electronicsstore.lab1javaee.controllers;

import com.electronicsstore.lab1javaee.repository.CustomerService;
import com.electronicsstore.lab1javaee.tables.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @PostMapping
    public String addCustomer(@RequestParam String name, @RequestParam String phoneNumber, @RequestParam String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        customerService.save(customer);
        return "redirect:/customers";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam int id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }
}