package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.Constants.JobRequestStatus;
import com.example.demo.FieldOps.DTO.Request.JobRequestRequestDTO;
import com.example.demo.FieldOps.DTO.Response.JobRequestResponseDTO;
import com.example.demo.FieldOps.Entity.Assets;
import com.example.demo.FieldOps.Entity.JobRequest;
import com.example.demo.FieldOps.Entity.User;
import com.example.demo.FieldOps.Exception.ResourceAlreadyExists;
import com.example.demo.FieldOps.Exception.ResourceNotFound;
import com.example.demo.FieldOps.Mapper.JobRequestMapper;
import com.example.demo.FieldOps.Repository.AssetsRepository;
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
public class JobRequestService {

    private final JobRequestRepository jobRequestRepository;
    private final JobRequestMapper jobRequestMapper;
    private final AssetsRepository assetsRepository;
    private final UserRepository userRepository;

    public JobRequestResponseDTO raiseRequest(@Valid JobRequestRequestDTO jobRequest) {

        Assets assets = assetsRepository.findById(jobRequest.getAssetId()).orElseThrow(() -> new ResourceNotFound("Assets not found:" + jobRequest.getAssetId()));
        User user = userRepository.findById(jobRequest.getCustomerId()).orElseThrow(() -> new ResourceNotFound("Customer not found:" + jobRequest.getCustomerId()));
        JobRequest request = new JobRequest();
        request.setTitle(jobRequest.getTitle());
        request.setNotes(jobRequest.getNotes());
        request.setStatus(JobRequestStatus.OPEN);
        request.setCreatedDate(LocalDateTime.now());
        request.setAsset(assets);
        request.setUser(user);
        return jobRequestMapper.toJobRequestResponseDTO(jobRequestRepository.save(request));
    }

    public List<JobRequestResponseDTO> allJobRequests() {
        return jobRequestRepository.findAll().stream().map(jobRequestMapper::toJobRequestResponseDTO).collect(Collectors.toList());
    }
}
