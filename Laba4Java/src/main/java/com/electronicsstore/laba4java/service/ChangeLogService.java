package com.electronicsstore.laba4java.service;

import com.electronicsstore.laba4java.table.ChangeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChangeLogService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void logChange(String changeType, String entityClass, int entityId, String changeDetails) {
        ChangeLog changeLog = new ChangeLog();
        changeLog.setChangeType(changeType);
        changeLog.setEntityClass(entityClass);
        changeLog.setEntityId(entityId);
        changeLog.setChangeDetails(changeDetails);
        changeLog.setChangeTimestamp(LocalDateTime.now());

        jmsTemplate.convertAndSend("change.log.queue", changeLog);
    }
}
