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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JobService {


    private final JobRepository jobRepository;
    private final JobRequestRepository jobRequestRepository;
    private final UserRepository userRepository;
    private final JobMapper jobMapper;
    private final EmailService emailService;


    public JobResponseDTO assign(Long requestId,
                                 JobRequestDTO dto) {

        JobRequest jobRequest = jobRequestRepository.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFound("Job Request not found : " + requestId));

        if (jobRepository.findByJobRequest(jobRequest).isPresent()) {
            throw new ResourceAlreadyExists("Job already assigned.");
        }

        User dispatcher = userRepository.findById(dto.getDispatcherId())
                .orElseThrow(() ->
                        new ResourceNotFound("Dispatcher not found"));

        User technician = userRepository.findById(dto.getTechnicianId())
                .orElseThrow(() ->
                        new ResourceNotFound("Technician not found"));

        Job job = new Job();

        job.setJobRequest(jobRequest);
        job.setDispatcher(dispatcher);
        job.setTechnician(technician);

        job.setStatus(JobStatus.ASSIGNED);

        job.setCreateTime(LocalDateTime.now());
        job.setUpdateTime(LocalDateTime.now());

        Job savedJob = jobRepository.save(job);

        emailService.sendJobAssignmentMail(
                technician.getEmail());
        log.info("Successfully the job is assigned to the technician");
        return jobMapper.toJobResponseDTO(savedJob);
    }


    public List<JobResponseDTO> allJobs() {
        log.info("Getting all jobs");
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toJobResponseDTO)
                .toList();
    }
}

