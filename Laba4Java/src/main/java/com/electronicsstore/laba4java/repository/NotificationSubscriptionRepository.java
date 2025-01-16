package com.electronicsstore.laba4java.repository;

import com.electronicsstore.laba4java.table.NotificationSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSubscriptionRepository extends JpaRepository<NotificationSubscription, Integer> { }