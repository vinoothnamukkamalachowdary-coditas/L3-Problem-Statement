package com.example.demo.FieldOps.DTO.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponseDTO {
    private Long id;
    private String issuedTo;
    private String message;
    private String recipientName;
    private LocalDateTime issuedAt;
    private String issuedByEmail;
}

