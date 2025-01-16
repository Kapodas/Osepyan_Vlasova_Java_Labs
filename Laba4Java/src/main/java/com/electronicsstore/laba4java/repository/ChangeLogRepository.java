package com.electronicsstore.laba4java.repository;

import com.electronicsstore.laba4java.table.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeLogRepository extends JpaRepository<ChangeLog, Integer> { }
