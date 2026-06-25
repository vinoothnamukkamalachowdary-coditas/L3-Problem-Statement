package com.example.demo.FieldOps.DTO.Request;

import com.example.demo.FieldOps.Entity.JobRequest;
import com.example.demo.FieldOps.Entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobRequestDTO {
    private Long id;
    private User assignedBy;
    private User assignedTo;
    private JobRequest jobRequest;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime endTime;
}
