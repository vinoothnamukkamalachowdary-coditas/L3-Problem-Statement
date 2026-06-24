package com.example.demo.FieldOps.Config;

import com.example.demo.FieldOps.Entity.User;
import com.example.demo.FieldOps.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.example.demo.FieldOps.Constants.Role.ROLE_ADMIN;

@Component
@RequiredArgsConstructor
public class DataSeederConfig implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        if(!userRepo.existsByEmail("vinoothnamukkamala@gmail.com")){
            User admin =  User.builder()
                    .name("Aniruddha")
                    .email("vinoothnamukkamala@gmail.com")
                    .password(passwordEncoder.encode("ani@3110"))
                    .role(ROLE_ADMIN)
                    .isActive(true)
                    .Address("Maharashtra")
                    .createdAt(LocalDateTime.now())
                    .build();

            userRepo.save(admin);
        }
    }
}
