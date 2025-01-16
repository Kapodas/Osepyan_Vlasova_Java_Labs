package com.electronicsstore.laba4java.controller;

import com.electronicsstore.laba4java.repository.CategoryRepository;
import com.electronicsstore.laba4java.repository.ProductRepository;
import com.electronicsstore.laba4java.service.ChangeLogService;
import com.electronicsstore.laba4java.table.Category;
import com.electronicsstore.laba4java.table.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ChangeLogService changeLogService;

    // Получить все продукты в формате XML с XSLT
    @GetMapping(produces = "application/xml")
    public ResponseEntity<String> getAllProductsXml(HttpServletResponse response) {
        List<Product> products = productRepository.findAll();

        // Преобразуем список продуктов в XML
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"/products.xsl\"?>\n" +
                "<List>\n";
        for (Product product : products) {
            xmlContent += "    <item>\n" +
                    "        <id>" + product.getId() + "</id>\n" +
                    "        <name>" + product.getName() + "</name>\n" +
                    "        <price>" + product.getPrice() + "</price>\n" +
                    "        <categoryId>" + product.getCategory().getId() + "</categoryId>\n" +
                    "    </item>\n";
        }
        xmlContent += "</List>";

        // Добавляем ссылку на XSLT в заголовок ответа
        response.addHeader("Content-Type", "application/xml");
        response.addHeader("Link", "</products.xsl>; rel=\"stylesheet\"; type=\"text/xsl\"");

        return new ResponseEntity<>(xmlContent, HttpStatus.OK);
    }

    // Получить все продукты
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Получить продукт по ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Создать новый продукт
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.getCategoryId()).orElse(null);
        if (category != null) {
            Product product = new Product();
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());
            product.setCategory(category);
            Product savedProduct = productRepository.save(product);
            changeLogService.logChange("INSERT", "Customer", savedProduct.getId(), savedProduct.toString());
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Обновить продукт по ID
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody ProductRequest productRequest) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            Category category = categoryRepository.findById(productRequest.getCategoryId()).orElse(null);
            if (category != null) {
                product.setName(productRequest.getName());
                product.setPrice(productRequest.getPrice());
                product.setCategory(category);
                Product updatedProduct = productRepository.save(product);
                changeLogService.logChange("UPDATE", "Customer", updatedProduct.getId(), updatedProduct.toString());
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удалить продукт по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int id) {
        try {
            productRepository.deleteById(id);
            changeLogService.logChange("DELETE", "Product", id, "Product deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Вспомогательный класс для запроса на создание/обновление продукта
    public static class ProductRequest {
        private String name;
        private double price;
        private int categoryId;

        // Геттеры и сеттеры
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }
    }
}