package com.electronicsstore.lab1javaee.repository;

import com.electronicsstore.lab1javaee.tables.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Здесь можно добавить дополнительные методы запросов, если необходимо
}