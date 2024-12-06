package com.electronicsstore.lab1javaee.repository;

import com.electronicsstore.lab1javaee.tables.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Здесь можно добавить дополнительные методы запросов, если необходимо
}