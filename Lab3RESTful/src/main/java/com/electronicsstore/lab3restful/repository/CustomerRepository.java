package com.electronicsstore.lab3restful.repository;

import com.electronicsstore.lab3restful.table.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> { }