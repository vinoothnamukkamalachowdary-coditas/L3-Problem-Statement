package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.*;
import com.example.demo.FieldOps.Repository.AssetAttachmentsRepository;
import com.example.demo.FieldOps.Repository.AssetsRepository;
import com.example.demo.FieldOps.Repository.JobRequestRepository;
import com.example.demo.FieldOps.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerDashboardService {

    private final UserRepository customerRepository;
    private final AssetsRepository assetRepository;
    private final AssetAttachmentsRepository attachmentRepository;
    private final JobRequestRepository jobRequestRepository;

    public CustomerDashBoardResponseDTO getCustomerDashboard(
            Long id, int page, int size, String sortBy, boolean ascending, String search
    ) {
        // 1. Configure sorting and pagination rules
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        // 2. Fetch data from your database (assuming standard JpaRepository methods exist)
        // Adjust the repository method names to match your real JPA query methods
        var activityPage = customerRepository.findActivitiesByUserId(id, search, pageable);

        // 3. Build and return the structured dashboard DTO
        return CustomerDashBoardResponseDTO.builder()
                .customerId(id)
                .activities(activityPage.getContent())
                .currentPage(activityPage.getNumber())
                .totalItems(activityPage.getTotalElements())
                .totalPages(activityPage.getTotalPages())
                .build();
    }

    public List<CustomerAssetsResponseDTO> getassetsByCustomerId(Long customerId) {
        return assetRepository.findByCustomerId(String.valueOf(customerId))
                .stream()
                .map(asset -> CustomerAssetsResponseDTO.builder()
                        .assetId(asset.getId())
                        .assetName(asset.getAssetName())
                        .assetType(String.valueOf(asset.getAssetType()))
                        .assetDescription(String.valueOf(asset.getAssetDescription()))
                        .assetCode(String.valueOf(asset.getAssetCode()))
                        .build())
                .collect(Collectors.toList());
    }

    public List<CustomerAssetAttachmentsResponseDTO> getassetAttachments(Long attachmentId) {
        return attachmentRepository.findByAssetId(attachmentId)
                .stream()
                .map(file -> CustomerAssetAttachmentsResponseDTO.builder()
                        .attachmentId(file.getId())
                        .fileName(file.getFileName())
                        .fileUrl(file.getFileUrl())
                        .fileType(file.getFileType())
                        .fileSize(file.getFileSize())
                        .build())
                .collect(Collectors.toList());
    }

    public List<CustomerJobRequestsResponseDTO> getJobRequests(Long customerId) {
        return jobRequestRepository.findByCustomerId(customerId)
                .stream()
                .map(job -> CustomerJobRequestsResponseDTO.builder()
                        .jobRequestId(job.getId())
                        .title(job.getTitle())
                        .jobRequestId(job.getJob().getId())
                        .notes(job.getNotes())
                        .build())
                .collect(Collectors.toList());
    }
}
