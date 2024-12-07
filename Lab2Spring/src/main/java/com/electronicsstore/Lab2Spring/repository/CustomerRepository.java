package com.electronicsstore.Lab2Spring.repository;

import com.electronicsstore.Lab2Spring.table.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Здесь можно добавить дополнительные методы запросов, если необходимо
}