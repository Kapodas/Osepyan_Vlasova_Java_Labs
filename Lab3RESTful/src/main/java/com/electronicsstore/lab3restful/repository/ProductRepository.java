package com.electronicsstore.lab3restful.repository;

import com.electronicsstore.lab3restful.table.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> { }
