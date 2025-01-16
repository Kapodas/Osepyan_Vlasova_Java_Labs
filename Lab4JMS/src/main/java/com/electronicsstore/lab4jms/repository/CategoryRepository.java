package com.electronicsstore.lab4jms.repository;

import com.electronicsstore.lab4jms.table.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> { }