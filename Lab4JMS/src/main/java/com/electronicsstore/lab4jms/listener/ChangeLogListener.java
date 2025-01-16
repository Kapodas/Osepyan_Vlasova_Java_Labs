package com.electronicsstore.lab4jms.listener;

import com.electronicsstore.lab4jms.repository.ChangeLogRepository;
import com.electronicsstore.lab4jms.service.NotificationService;
import com.electronicsstore.lab4jms.table.ChangeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ChangeLogListener {

    @Autowired
    private ChangeLogRepository changeLogRepository;

    @Autowired
    private NotificationService notificationService;

    @JmsListener(destination = "change.log.queue")
    public void receiveMessage(ChangeLog changeLog) {
        try {
            changeLogRepository.save(changeLog);
            notificationService.checkAndSendNotifications(changeLog);
        } catch (Exception e) {
            System.out.println("Failed to process change log message " + e);
        }
    }
}