package com.example.demo.FieldOps.DTO.Request;

import com.example.demo.FieldOps.Entity.JobRequest;
import com.example.demo.FieldOps.Entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobRequestDTO {
    private Long id;
    private Long assignedBy;
    private Long assignedTo;
    private Long jobRequest;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime endTime;
}
