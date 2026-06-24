package com.example.demo.FieldOps.Controller;

import com.example.demo.FieldOps.DTO.Request.UserLoginRequestDTO;
import com.example.demo.FieldOps.DTO.Request.UserRegisterRequestDTO;
import com.example.demo.FieldOps.DTO.Response.UserLoginResponseDTO;
import com.example.demo.FieldOps.DTO.Response.UserRegisterResponseDTO;
import com.example.demo.FieldOps.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO>  registerUser( @Valid @RequestBody UserRegisterRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> loginUser(@Valid @RequestBody UserLoginRequestDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.loginUser(loginDTO));
    }
}
