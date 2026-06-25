package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.Constants.JobRequestStatus;
import com.example.demo.FieldOps.DTO.Request.JobRequestRequestDTO;
import com.example.demo.FieldOps.DTO.Response.JobRequestResponseDTO;
import com.example.demo.FieldOps.Entity.JobRequest;
import com.example.demo.FieldOps.Exception.ResourceAlreadyExists;
import com.example.demo.FieldOps.Mapper.JobRequestMapper;
import com.example.demo.FieldOps.Repository.JobRequestRepository;
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
public class JobRequestService {

    private final JobRequestRepository jobRequestRepository;
    private final JobRequestMapper jobRequestMapper;

    public JobRequestResponseDTO raiseRequest(@Valid JobRequestRequestDTO jobRequest) {
        if (jobRequestRepository.existsByJobRequestId(jobRequest.getCreatedBy().getId())){
            throw new ResourceAlreadyExists("Job Request already exists");
        }
        JobRequest request = new JobRequest();
        request.setId(jobRequest.getCreatedBy().getId());
        request.setTitle(jobRequest.getTitle());
        request.setNotes(jobRequest.getNotes());
        request.setStatus(JobRequestStatus.OPEN);
        request.setAsset(jobRequest.getAssets());
        request.setUser(jobRequest.getCreatedBy());
        request.setCreatedDate(LocalDateTime.now());
        return jobRequestMapper.toJobRequestResponseDTO(jobRequestRepository.save(request));
    }

    public List<JobRequestResponseDTO> allJobRequests() {
        return jobRequestRepository.findAll().stream().map(jobRequestMapper::toJobRequestResponseDTO).collect(Collectors.toList());
    }
}
