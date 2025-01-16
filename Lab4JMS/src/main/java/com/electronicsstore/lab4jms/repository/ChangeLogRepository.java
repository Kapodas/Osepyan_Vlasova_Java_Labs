package com.electronicsstore.lab4jms.repository;

import com.electronicsstore.lab4jms.table.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeLogRepository extends JpaRepository<ChangeLog, Integer> { }
