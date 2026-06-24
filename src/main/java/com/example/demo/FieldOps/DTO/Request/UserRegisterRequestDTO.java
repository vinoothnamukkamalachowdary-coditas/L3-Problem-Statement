package com.example.demo.FieldOps.DTO.Request;

import com.example.demo.FieldOps.Constants.Role;
import lombok.Data;

@Data
public class UserRegisterRequestDTO {
    private String name;
    private String email;
    private String password;
    private String Address;
    private Role role;
}
