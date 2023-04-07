package com.example.adoptame.application.entities.auditLog.services;

import com.example.adoptame.application.entities.auditLog.AuditLog;
import com.example.adoptame.application.entities.auditLog.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginLog {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public void saveLogin(String username) {
        AuditLog auditLog = new AuditLog();
        auditLog.setUsername(username);

        auditLog.setDate(LocalDateTime.now());
        auditLogRepository.save(auditLog);
    }
}
