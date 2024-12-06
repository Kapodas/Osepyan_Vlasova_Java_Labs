package com.electronicsstore.lab1javaee.repository;

import com.electronicsstore.lab1javaee.tables.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Здесь можно добавить дополнительные методы запросов, если необходимо
}