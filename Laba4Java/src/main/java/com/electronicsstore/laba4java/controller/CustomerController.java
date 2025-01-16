package com.electronicsstore.laba4java.controller;

import com.electronicsstore.laba4java.repository.CustomerRepository;
import com.electronicsstore.laba4java.service.ChangeLogService;
import com.electronicsstore.laba4java.table.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ChangeLogService changeLogService;

    // Получить всех клиентов
    @GetMapping(produces = "application/xml")
    public ResponseEntity<String> getAllCustomersXml(HttpServletResponse response) {
        List<Customer> customers = customerRepository.findAll();

        // Преобразуем список клиентов в XML
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"/customers.xsl\"?>\n" +
                "<List>\n";
        for (Customer customer : customers) {
            xmlContent += "    <item>\n" +
                    "        <id>" + customer.getId() + "</id>\n" +
                    "        <name>" + customer.getName() + "</name>\n" +
                    "        <phoneNumber>" + customer.getPhoneNumber() + "</phoneNumber>\n" +
                    "        <email>" + customer.getEmail() + "</email>\n" +
                    "    </item>\n";
        }
        xmlContent += "</List>";

        // Добавляем ссылку на XSLT в заголовок ответа
        response.addHeader("Content-Type", "application/xml");
        response.addHeader("Link", "</customers.xsl>; rel=\"stylesheet\"; type=\"text/xsl\"");

        return new ResponseEntity<>(xmlContent, HttpStatus.OK);
    }

    // Получить клиента по ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Создать нового клиента
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        changeLogService.logChange("INSERT", "Customer", savedCustomer.getId(), savedCustomer.toString());
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    // Обновить клиента по ID
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customerDetails) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(customerDetails.getName());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setEmail(customerDetails.getEmail());
            Customer updatedCustomer = customerRepository.save(customer);
            changeLogService.logChange("UPDATE", "Customer", updatedCustomer.getId(), updatedCustomer.toString());
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удалить клиента по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable int id) {
        try {
            customerRepository.deleteById(id);
            changeLogService.logChange("DELETE", "Customer", id, "Customer deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}