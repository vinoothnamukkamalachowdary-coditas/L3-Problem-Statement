package com.example.demo.FieldOps.DTO.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponseDTO {
    private Long id;
    private String issuedTo;
    private LocalDateTime issuedAt;
    private String issuedByEmail;
}
