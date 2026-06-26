package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.DTO.Request.UserLoginRequestDTO;
import com.example.demo.FieldOps.DTO.Request.UserRegisterRequestDTO;
import com.example.demo.FieldOps.DTO.Response.UserLoginResponseDTO;
import com.example.demo.FieldOps.DTO.Response.UserRegisterResponseDTO;
import com.example.demo.FieldOps.Entity.User;
import com.example.demo.FieldOps.Exception.ResourceAlreadyExists;
import com.example.demo.FieldOps.Exception.ResourceNotFound;
import com.example.demo.FieldOps.Mapper.UserMapper;
import com.example.demo.FieldOps.Repository.UserRepository;
import com.example.demo.FieldOps.Security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserRegisterResponseDTO registerUser(@Valid UserRegisterRequestDTO dto) {
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new ResourceAlreadyExists("User Already Present");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setActive(true);
        user.setAddress(dto.getAddress());
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        log.info("Registering user");
        return userMapper.Response(userRepository.save(user));
    }

    public UserLoginResponseDTO loginUser(@Valid UserLoginRequestDTO loginDTO) {
        User user1 = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new ResourceNotFound("User Not Found"));
        if(!passwordEncoder.matches(loginDTO.getPassword(),user1.getPassword())){
            throw new RuntimeException("Wrong Password");
        }
        String token = jwtUtil.generateToken(user1.getEmail(), user1.getRole().name());
        log.info("Login Token: {}", token);
        return UserLoginResponseDTO.builder()
                .token(token)
                .name(user1.getName())
                .email(user1.getEmail())
                .role(user1.getRole())
                .build();
    }
}
