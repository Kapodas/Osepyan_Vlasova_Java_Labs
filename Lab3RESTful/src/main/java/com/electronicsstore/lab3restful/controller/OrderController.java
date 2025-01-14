package com.electronicsstore.lab3restful.controller;

import com.electronicsstore.lab3restful.repository.CustomerRepository;
import com.electronicsstore.lab3restful.repository.OrderRepository;
import com.electronicsstore.lab3restful.repository.ProductRepository;
import com.electronicsstore.lab3restful.table.Customer;
import com.electronicsstore.lab3restful.table.Order;
import com.electronicsstore.lab3restful.table.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    // Получить все заказы в формате XML с XSLT
    @GetMapping(produces = "application/xml")
    public ResponseEntity<String> getAllOrdersXml(HttpServletResponse response) {
        List<Order> orders = orderRepository.findAll();

        // Преобразуем список заказов в XML
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"/orders.xsl\"?>\n" +
                "<List>\n";
        for (Order order : orders) {
            xmlContent += "    <item>\n" +
                    "        <id>" + order.getId() + "</id>\n" +
                    "        <customerId>" + order.getCustomer().getId() + "</customerId>\n" +
                    "        <productId>" + order.getProduct().getId() + "</productId>\n" +
                    "        <orderDate>" + order.getOrderDate() + "</orderDate>\n" +
                    "        <quantity>" + order.getQuantity() + "</quantity>\n" +
                    "    </item>\n";
        }
        xmlContent += "</List>";

        // Добавляем ссылку на XSLT в заголовок ответа
        response.addHeader("Content-Type", "application/xml");
        response.addHeader("Link", "</orders.xsl>; rel=\"stylesheet\"; type=\"text/xsl\"");

        return new ResponseEntity<>(xmlContent, HttpStatus.OK);
    }

    // Получить все заказы
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Получить заказ по ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Создать новый заказ
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.getCustomerId()).orElse(null);
        Product product = productRepository.findById(orderRequest.getProductId()).orElse(null);

        if (customer != null && product != null) {
            Order order = new Order();
            order.setCustomer(customer);
            order.setProduct(product);
            order.setOrderDate(orderRequest.getOrderDate());
            order.setQuantity(orderRequest.getQuantity());
            Order savedOrder = orderRepository.save(order);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Обновить заказ по ID
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody OrderRequest orderRequest) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            Customer customer = customerRepository.findById(orderRequest.getCustomerId()).orElse(null);
            Product product = productRepository.findById(orderRequest.getProductId()).orElse(null);

            if (customer != null && product != null) {
                order.setCustomer(customer);
                order.setProduct(product);
                order.setOrderDate(orderRequest.getOrderDate());
                order.setQuantity(orderRequest.getQuantity());
                Order updatedOrder = orderRepository.save(order);
                return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удалить заказ по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable int id) {
        try {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Вспомогательный класс для запроса на создание/обновление заказа
    public static class OrderRequest {
        private int customerId;
        private int productId;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date orderDate;
        private int quantity;

        // Геттеры и сеттеры
        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public Date getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}