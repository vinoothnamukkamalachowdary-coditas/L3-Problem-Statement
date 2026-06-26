package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.JobResponseDTO;
import com.example.demo.FieldOps.Entity.Job;
import org.springframework.stereotype.Component;


@Component
public class JobMapper {
    public JobResponseDTO toJobResponseDTO(Job job) {

        JobResponseDTO dto = new JobResponseDTO();

        dto.setId(job.getId());

        if (job.getDispatcher() != null)
            dto.setDispatcherId(job.getDispatcher().getId());

        if (job.getTechnician() != null)
            dto.setTechnicianId(job.getTechnician().getId());

        if (job.getJobRequest() != null)
            dto.setJobRequestId(job.getJobRequest().getId());

        dto.setCreateTime(job.getCreateTime());
        dto.setUpdateTime(job.getUpdateTime());
        dto.setEndTime(job.getEndTime());

        return dto;
    }

}
