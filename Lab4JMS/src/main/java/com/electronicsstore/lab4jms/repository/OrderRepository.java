package com.electronicsstore.lab4jms.repository;

import com.electronicsstore.lab4jms.table.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> { }