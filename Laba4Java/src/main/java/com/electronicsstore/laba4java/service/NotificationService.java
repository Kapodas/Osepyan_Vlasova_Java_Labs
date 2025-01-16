package com.electronicsstore.laba4java.service;

import com.electronicsstore.laba4java.repository.NotificationSubscriptionRepository;
import com.electronicsstore.laba4java.table.ChangeLog;
import com.electronicsstore.laba4java.table.NotificationSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationSubscriptionRepository subscriptionRepository;

    public void sendNotification(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void checkAndSendNotifications(ChangeLog changeLog) {
        List<NotificationSubscription> subscriptions = subscriptionRepository.findAll();
        for (NotificationSubscription subscription : subscriptions) {
            if (shouldSendNotification(subscription, changeLog)) {
                sendNotification(
                        subscription.getEmail(),
                        "Change Notification " + subscription.getConditionValue(),
                        "Change detected: " + changeLog.toString()
                );
            }
        }
    }

    private boolean shouldSendNotification(NotificationSubscription subscription, ChangeLog changeLog) {
        String conditionType = subscription.getConditionType();
        String conditionValue = subscription.getConditionValue();

        switch (conditionType) {
            case "ALL":
                return true;
            case "ENTITY_TYPE":
                return changeLog.getEntityClass().equals(conditionValue);
            case "OPERATION_TYPE":
                return changeLog.getChangeType().equals(conditionValue);
            default:
                return false;
        }
    }
}