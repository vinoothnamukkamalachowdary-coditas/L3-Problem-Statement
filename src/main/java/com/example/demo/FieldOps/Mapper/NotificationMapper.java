package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.NotificationResponseDTO;
import com.example.demo.FieldOps.Entity.Notification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {
    public NotificationResponseDTO toNotificationResponseDTO(Notification notification) {

        NotificationResponseDTO dto = new NotificationResponseDTO();

        dto.setId(notification.getId());
        dto.setIssuedTo(notification.getIssuedTo());
        dto.setRecipientName(notification.getRecipientName());
        dto.setMessage(notification.getMessage());

        if (notification.getIssuedBy() != null)
            dto.setIssuedByEmail(notification.getIssuedBy().getEmail());

        dto.setIssuedAt(LocalDateTime.now());

        return dto;
    }
}
