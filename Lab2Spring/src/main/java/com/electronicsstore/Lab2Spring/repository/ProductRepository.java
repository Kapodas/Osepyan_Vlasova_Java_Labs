package com.electronicsstore.Lab2Spring.repository;

import com.electronicsstore.Lab2Spring.table.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Здесь можно добавить дополнительные методы запросов, если необходимо
}
