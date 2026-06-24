package com.example.demo.FieldOps.DTO.Response;

import com.example.demo.FieldOps.Constants.Role;
import lombok.Data;

@Data
public class UserRegisterResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String Address;
    private Role role;
}
