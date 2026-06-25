package com.example.demo.FieldOps.DTO.Request;

import com.example.demo.FieldOps.Entity.JobRequest;
import com.example.demo.FieldOps.Entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobRequestDTO {
    private Long dispatcherId;
    private Long technicianId;
    private Long jobRequestId;
//    private JobRequest jobRequest;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime endTime;
}
