package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.UserLoginResponseDTO;
import com.example.demo.FieldOps.DTO.Response.UserRegisterResponseDTO;
import com.example.demo.FieldOps.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
//    public UserLoginResponseDTO toResponse(User user) {
//        UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
//        userLoginResponseDTO.setName(user.getName());
//        userLoginResponseDTO.setRole(user.getRole());
//        userLoginResponseDTO.setEmail(user.getEmail());
//        return userLoginResponseDTO;
//    }
    public UserRegisterResponseDTO Response(User user1) {
        UserRegisterResponseDTO userRegisterResponseDTO = new UserRegisterResponseDTO();
        userRegisterResponseDTO.setName(user1.getName());
        userRegisterResponseDTO.setRole(user1.getRole());
        userRegisterResponseDTO.setEmail(user1.getEmail());
        userRegisterResponseDTO.setPassword(user1.getPassword());
        userRegisterResponseDTO.setAddress(user1.getAddress());
        return userRegisterResponseDTO;
    }
}
