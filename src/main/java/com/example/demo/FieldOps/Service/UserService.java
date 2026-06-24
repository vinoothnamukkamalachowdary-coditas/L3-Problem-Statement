package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.DTO.Response.UserResponseDTO;
import com.example.demo.FieldOps.Entity.User;
import com.example.demo.FieldOps.Exception.ResourceNotFound;
import com.example.demo.FieldOps.Mapper.UserMapper;
import com.example.demo.FieldOps.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse).collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toResponse).orElseThrow(() -> new ResourceNotFound("User is Not Found with the id " + id));
    }

    public UserResponseDTO updateUser(Long ID, @Valid User user) {
        User user1 = userRepository.findById(ID).orElseThrow(() -> new ResourceNotFound("User Is Not Found"));
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setAddress(user.getAddress());
        return  userMapper.toResponse(userRepository.save(user1));
    }

    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User is Not Found with the id " + id));
        user.setActive(false);
        userRepository.save(user);
        return "User has been deleted";
    }
}
