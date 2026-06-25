package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.Constants.JobStatus;
import com.example.demo.FieldOps.DTO.Request.JobRequestDTO;
import com.example.demo.FieldOps.DTO.Response.JobResponseDTO;
import com.example.demo.FieldOps.Entity.Job;
import com.example.demo.FieldOps.Exception.ResourceAlreadyExists;
import com.example.demo.FieldOps.Mapper.JobMapper;
import com.example.demo.FieldOps.Repository.JobRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobResponseDTO assign(Long id, @Valid JobRequestDTO jobRequest) {
        if(jobRepository.existsByJobId(id)) {
            throw new ResourceAlreadyExists("Job already exists");
        }
        if(!(jobRepository.findByJobRequestId(id).isEmpty())) {
            throw new ResourceAlreadyExists("Job already exists for the request");
        }
        Job job = new Job();
        job.setId(jobRequest.getId());
        job.setCreateTime(LocalDateTime.now());
        job.setStatus(JobStatus.ASSIGNED);
        job.setJobRequest(jobRequest.getJobRequest());
        job.setDispatcher(jobRequest.getAssignedBy());
        job.setTechnician(jobRequest.getAssignedTo());
        job.setCreateTime(LocalDateTime.now());
        return jobMapper.toJobResponseDTO(jobRepository.save(job));
    }

    public List<JobResponseDTO> allJobs() {
        return jobRepository.findAll().stream().map(jobMapper::toJobResponseDTO).collect(Collectors.toList());
    }
}
