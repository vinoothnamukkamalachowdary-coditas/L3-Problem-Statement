package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.UserLoginResponseDTO;
import com.example.demo.FieldOps.DTO.Response.UserRegisterResponseDTO;
import com.example.demo.FieldOps.DTO.Response.UserResponseDTO;
import com.example.demo.FieldOps.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toResponse(User user) {
        UserResponseDTO ResponseDTO = new UserResponseDTO();
        ResponseDTO.setId(user.getId());
        ResponseDTO.setName(user.getName());
        ResponseDTO.setRole(user.getRole());
        ResponseDTO.setEmail(user.getEmail());
        ResponseDTO.setAddress(user.getAddress());
        ResponseDTO.setPassword(user.getPassword());
        ResponseDTO.setActive(user.isActive());
        ResponseDTO.setCreatedAt(user.getCreatedAt());
        ResponseDTO.setUpdatedAt(user.getUpdatedAt());
        return ResponseDTO;
    }

    public UserRegisterResponseDTO Response(User user1) {
        UserRegisterResponseDTO userRegisterResponseDTO = new UserRegisterResponseDTO();
        userRegisterResponseDTO.setId(user1.getId());
        userRegisterResponseDTO.setName(user1.getName());
        userRegisterResponseDTO.setRole(user1.getRole());
        userRegisterResponseDTO.setEmail(user1.getEmail());
        userRegisterResponseDTO.setPassword(user1.getPassword());
        userRegisterResponseDTO.setAddress(user1.getAddress());
        return userRegisterResponseDTO;
    }
}
