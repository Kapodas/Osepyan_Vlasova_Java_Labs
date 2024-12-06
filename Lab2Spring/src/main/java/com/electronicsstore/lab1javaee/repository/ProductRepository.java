package com.electronicsstore.lab1javaee.repository;

import com.electronicsstore.lab1javaee.tables.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Здесь можно добавить дополнительные методы запросов, если необходимо
}
