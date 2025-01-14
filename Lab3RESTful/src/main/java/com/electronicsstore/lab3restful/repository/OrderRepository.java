package com.electronicsstore.lab3restful.repository;

import com.electronicsstore.lab3restful.table.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> { }