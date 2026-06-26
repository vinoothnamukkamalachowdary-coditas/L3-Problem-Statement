package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.JobRequestResponseDTO;
import com.example.demo.FieldOps.Entity.JobRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JobRequestMapper {
    public JobRequestResponseDTO toJobRequestResponseDTO(JobRequest jobRequest){
        JobRequestResponseDTO jobRequestResponseDTO = new JobRequestResponseDTO();
        jobRequestResponseDTO.setId(jobRequest.getId());
        jobRequestResponseDTO.setTitle(jobRequest.getTitle());
        jobRequestResponseDTO.setNotes(jobRequest.getNotes());
        jobRequestResponseDTO.setStatus(jobRequest.getStatus());
        if (jobRequest.getAsset() != null)
            jobRequestResponseDTO.setAssetId(jobRequest.getAsset().getId());

        if (jobRequest.getUser() != null)
            jobRequestResponseDTO.setCustomerId(jobRequest.getUser().getId());
        jobRequestResponseDTO.setCreatedDate(LocalDateTime.now());
        jobRequestResponseDTO.setUpdatedDate(LocalDateTime.now());
        return jobRequestResponseDTO;
    }
}

