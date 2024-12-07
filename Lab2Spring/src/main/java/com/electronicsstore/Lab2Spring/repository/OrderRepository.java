package com.electronicsstore.Lab2Spring.repository;

import com.electronicsstore.Lab2Spring.table.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Здесь можно добавить дополнительные методы запросов, если необходимо
}