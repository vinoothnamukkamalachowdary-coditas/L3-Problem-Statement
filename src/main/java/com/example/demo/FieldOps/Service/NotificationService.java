package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.DTO.Request.NotificationRequestDTO;
import com.example.demo.FieldOps.DTO.Response.NotificationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {
    public NotificationResponseDTO sendNotification(Long jobId, @Valid NotificationRequestDTO notificationRequestDTO) {
    }
}
