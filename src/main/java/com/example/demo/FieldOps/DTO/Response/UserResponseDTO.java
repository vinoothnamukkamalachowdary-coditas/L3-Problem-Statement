package com.example.demo.FieldOps.DTO.Response;

import com.example.demo.FieldOps.Constants.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private boolean isActive;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
