package com.electronicsstore.Lab2Spring.controller;

import com.electronicsstore.Lab2Spring.repository.ProductRepository;
import com.electronicsstore.Lab2Spring.repository.CategoryRepository;
import com.electronicsstore.Lab2Spring.table.Product;
import com.electronicsstore.Lab2Spring.table.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping
    public String addProduct(@RequestParam String name, @RequestParam double price, @RequestParam int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setCategory(category);
            productRepository.save(product);
        }
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable int id, @RequestParam String name, @RequestParam double price, @RequestParam int categoryId) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            if (category != null) {
                product.setName(name);
                product.setPrice(price);
                product.setCategory(category);
                productRepository.save(product);
            }
        }
        return "redirect:/products";
    }
}