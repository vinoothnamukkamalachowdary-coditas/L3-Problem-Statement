package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.NotificationResponseDTO;
import com.example.demo.FieldOps.Entity.Notification;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {
    public NotificationResponseDTO toNotificationResponseDTO(Notification notification) {
        NotificationResponseDTO notificationResponseDTO = new NotificationResponseDTO();
        notificationResponseDTO.setId(notification.getId());
        notificationResponseDTO.setIssuedTo(notification.getIssuedTo());
        notificationResponseDTO.setIssuedByEmail(notification.getIssuedBy().getEmail());
        notificationResponseDTO.setIssuedAt(LocalDateTime.now());
        return  notificationResponseDTO;
    }
}
