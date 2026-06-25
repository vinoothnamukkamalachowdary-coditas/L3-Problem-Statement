package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.JobResponseDTO;
import com.example.demo.FieldOps.Entity.Job;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JobMapper {
    public JobResponseDTO toJobResponseDTO(Job job) {
        JobResponseDTO jobResponseDTO = new JobResponseDTO();
        jobResponseDTO.setId(job.getId());
        jobResponseDTO.setJobRequestId(jobResponseDTO.getJobRequestId());
        jobResponseDTO.setDispatcherId(jobResponseDTO.getDispatcherId());
        jobResponseDTO.setTechnicianId(jobResponseDTO.getTechnicianId());
        jobResponseDTO.setCreateTime(LocalDateTime.now());
        jobResponseDTO.setUpdateTime(LocalDateTime.now());
        jobResponseDTO.setEndTime(LocalDateTime.now());
        return jobResponseDTO;
    }
}
