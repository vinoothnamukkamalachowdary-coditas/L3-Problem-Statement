package com.example.demo.FieldOps.DTO.Response;

import com.example.demo.FieldOps.Entity.JobRequest;
import com.example.demo.FieldOps.Entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobResponseDTO {
    private Long id;
    private Long dispatcherId;
    private Long technicianId;
    private Long jobRequestId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime endTime;
}
