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
    private final AssetsRepository assetsRepository;
    private final UserRepository userRepository;
    private final JobRequestMapper jobRequestMapper;

    public JobRequestResponseDTO raiseRequest(@Valid JobRequestRequestDTO requestDTO) {

        Assets asset = assetsRepository.findById(requestDTO.getAssetId())
                .orElseThrow(() ->
                        new ResourceNotFound("Asset not found with id : " + requestDTO.getAssetId()));

        User customer = userRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFound("Customer not found with id : " + requestDTO.getCustomerId()));

        JobRequest request = new JobRequest();

        request.setTitle(requestDTO.getTitle());
        request.setNotes(requestDTO.getNotes());
        request.setStatus(JobRequestStatus.OPEN);

        request.setAsset(asset.getId());
        request.setUser(customer);

        request.setCreatedDate(LocalDateTime.now());
        request.setUpdatedDate(LocalDateTime.now());
        JobRequest savedRequest = jobRequestRepository.save(request);

        return jobRequestMapper.toJobRequestResponseDTO(savedRequest);
    }

    @Transactional(readOnly = true)
    public List<JobRequestResponseDTO> allJobRequests() {

        return jobRequestRepository.findAll()
                .stream()
                .map(jobRequestMapper::toJobRequestResponseDTO)
                .collect(Collectors.toList());
    }
}
