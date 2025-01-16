package com.electronicsstore.laba4java.repository;

import com.electronicsstore.laba4java.table.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> { }