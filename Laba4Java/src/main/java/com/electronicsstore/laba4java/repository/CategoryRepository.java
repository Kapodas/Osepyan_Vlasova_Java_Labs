package com.electronicsstore.laba4java.repository;

import com.electronicsstore.laba4java.table.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> { }