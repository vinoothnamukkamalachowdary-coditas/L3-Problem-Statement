package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.Constants.JobStatus;
import com.example.demo.FieldOps.DTO.Request.JobRequestDTO;
import com.example.demo.FieldOps.DTO.Response.JobResponseDTO;
import com.example.demo.FieldOps.Entity.Job;
import com.example.demo.FieldOps.Entity.JobRequest;
import com.example.demo.FieldOps.Entity.User;
import com.example.demo.FieldOps.Exception.ResourceAlreadyExists;
import com.example.demo.FieldOps.Exception.ResourceNotFound;
import com.example.demo.FieldOps.Mapper.JobMapper;
import com.example.demo.FieldOps.Repository.JobRepository;
import com.example.demo.FieldOps.Repository.JobRequestRepository;
import com.example.demo.FieldOps.Repository.UserRepository;
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
    private final JobRequestRepository jobRequestRepository;
    private final JobMapper jobMapper;
    private final UserRepository userRepository;

    public JobResponseDTO assign(Long id, @Valid JobRequestDTO jobRequest) {
        if(jobRepository.existsById(id)) {
            throw new ResourceAlreadyExists("Job already exists");
        }
        JobRequest jr = jobRequestRepository.findById(jobRequest.getJobRequestId()).orElseThrow(()-> new ResourceNotFound("Job Request not found:" + jobRequest.getJobRequestId()));
        User assignedBy = userRepository.findById(jobRequest.getDispatcherId()).orElseThrow(()-> new ResourceNotFound("User Not Found:" + jobRequest.getDispatcherId()));
        User assignedTo = userRepository.findById(jobRequest.getTechnicianId()).orElseThrow(()-> new ResourceNotFound("User Not Found:" + jobRequest.getTechnicianId()));
        Job job = new Job();
        job.setId(jobRequest.getId());
        job.setCreateTime(LocalDateTime.now());
        job.setStatus(JobStatus.ASSIGNED);
        job.setCreateTime(LocalDateTime.now());
        job.setJobRequest(jr);
        job.setDispatcher(assignedBy);
        job.setTechnician(assignedTo);
        return jobMapper.toJobResponseDTO(jobRepository.save(job));
    }

    public List<JobResponseDTO> allJobs() {
        return jobRepository.findAll().stream().map(jobMapper::toJobResponseDTO).collect(Collectors.toList());
    }
}
