package com.electronicsstore.laba4java.listener;

import com.electronicsstore.laba4java.service.NotificationService;
import com.electronicsstore.laba4java.table.ChangeLog;
import com.electronicsstore.laba4java.repository.ChangeLogRepository;
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