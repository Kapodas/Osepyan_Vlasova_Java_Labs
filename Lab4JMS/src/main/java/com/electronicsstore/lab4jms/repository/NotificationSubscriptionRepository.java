package com.electronicsstore.lab4jms.repository;

import com.electronicsstore.lab4jms.table.NotificationSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSubscriptionRepository extends JpaRepository<NotificationSubscription, Integer> { }