package com.electronicsstore.lab3restful.repository;

import com.electronicsstore.lab3restful.table.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> { }