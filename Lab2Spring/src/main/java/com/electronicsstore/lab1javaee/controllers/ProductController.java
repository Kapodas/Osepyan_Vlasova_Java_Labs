package com.electronicsstore.lab1javaee.controllers;

import com.electronicsstore.lab1javaee.repository.ProductService;
import com.electronicsstore.lab1javaee.tables.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping
    public String addProduct(@RequestParam String name, @RequestParam double price, @RequestParam int categoryId) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam int id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @PostMapping("/update")
    public String updateProduct(@RequestParam int id, @RequestParam String name, @RequestParam double price, @RequestParam int categoryId) {
        Product product = productService.findById(id).orElse(null);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            product.setCategoryId(categoryId);
            productService.save(product);
        }
        return "redirect:/products";
    }
}