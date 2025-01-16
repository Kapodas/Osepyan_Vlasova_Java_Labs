package com.electronicsstore.lab4jms.repository;

import com.electronicsstore.lab4jms.table.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> { }