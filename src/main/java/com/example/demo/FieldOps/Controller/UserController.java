package com.example.demo.FieldOps.Controller;

import com.example.demo.FieldOps.DTO.Response.UserResponseDTO;
import com.example.demo.FieldOps.Entity.User;
import com.example.demo.FieldOps.Service.UserService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>>  getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<UserResponseDTO>  getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/update/{ID}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long ID,@Valid @RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(ID,user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
