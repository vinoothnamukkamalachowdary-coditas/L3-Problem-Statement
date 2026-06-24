package com.example.demo.FieldOps.DTO.Response;

import com.example.demo.FieldOps.Constants.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDTO {
    private String token;
    private String name;
    private String email;
    private Role role;
}
