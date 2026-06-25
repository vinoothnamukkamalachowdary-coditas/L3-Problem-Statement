package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.DTO.Request.JobRequestRequestDTO;
import com.example.demo.FieldOps.DTO.Response.JobResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class JobService {
    public JobResponseDTO assign(Long id, @Valid JobRequestRequestDTO jobRequest) {
    }
}
