package com.electronicsstore.Lab2Spring.repository;

import com.electronicsstore.Lab2Spring.table.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Здесь можно добавить дополнительные методы запросов, если необходимо
}