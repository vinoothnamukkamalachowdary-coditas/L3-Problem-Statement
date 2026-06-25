package com.example.demo.FieldOps.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationRequestDTO {
    @NotBlank(message = "Do not leave Blank")
    @Email(message = "Enter a valid Email")
    private String issuedTo;

    @NotNull(message = "Should not be null")
    private String recipientName;

    @NotNull(message = "Should not be null")
    private String message;



}
