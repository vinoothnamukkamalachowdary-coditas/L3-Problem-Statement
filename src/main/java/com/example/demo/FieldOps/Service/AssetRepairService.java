package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.Constants.JobRequestStatus;
import com.example.demo.FieldOps.Constants.JobStatus;
import com.example.demo.FieldOps.Constants.RepairStatus;
import com.example.demo.FieldOps.DTO.Response.AssetRepairResponseDTO;
import com.example.demo.FieldOps.DTO.Response.JobResponseDTO;
import com.example.demo.FieldOps.Entity.AssetRepair;
import com.example.demo.FieldOps.Entity.Job;
import com.example.demo.FieldOps.Entity.JobRequest;
import com.example.demo.FieldOps.Entity.User;
import com.example.demo.FieldOps.Exception.ResourceAlreadyExists;
import com.example.demo.FieldOps.Exception.ResourceNotFound;
import com.example.demo.FieldOps.Mapper.AssetRepairMapper;
import com.example.demo.FieldOps.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetRepairService {

    private final AssetRepairRepository assetRepairRepository;
    private final JobRepository jobRepository;
    private final JobRequestRepository jobRequestRepository;
    private final AssetsRepository assetsRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    private final EmailService emailService;
    private final AssetRepairMapper assetRepairMapper;

    public JobResponseDTO acceptJob(Long jobId, UserDetails userDetails) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFound("Job Not Found"));

        job.setStatus(JobStatus.ASSIGNED);

        job.setUpdateTime(LocalDateTime.now());

        Job savedJob = jobRepository.save(job);

        JobResponseDTO dto = new JobResponseDTO();

        dto.setId(savedJob.getId());
        dto.setDispatcherId(savedJob.getDispatcher().getId());
        dto.setTechnicianId(savedJob.getTechnician().getId());
        dto.setJobRequestId(savedJob.getJobRequest().getId());
        dto.setCreateTime(savedJob.getCreateTime());
        dto.setUpdateTime(savedJob.getUpdateTime());
        dto.setEndTime(savedJob.getEndTime());

        return dto;
    }

    public AssetRepairResponseDTO startRepair(Long jobId,
                                              String remarks,
                                              MultipartFile beforePhoto,
                                              UserDetails userDetails) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFound("Job Not Found"));

        if (assetRepairRepository.existsByJob(job)) {
            throw new ResourceAlreadyExists("Repair already started");
        }

        User technician = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFound("Technician Not Found"));

        String photoPath = fileStorageService.uploadFile(beforePhoto);

        AssetRepair repair = new AssetRepair();

        repair.setJob(job);

        repair.setRepairedAsset(job.getJobRequest().getAsset());

        repair.setRepairedBy(technician);

        repair.setBeforePhoto(photoPath);

        repair.setRemarks(remarks);

        repair.setStartedAt(LocalDateTime.now());

        repair.setStatus(RepairStatus.STARTED);

        AssetRepair savedRepair = assetRepairRepository.save(repair);

        job.setStatus(JobStatus.IN_PROGRESS);

        job.setUpdateTime(LocalDateTime.now());

        jobRepository.save(job);

        return assetRepairMapper.toDto(savedRepair);
    }

    public AssetRepairResponseDTO completeRepair(Long repairId,
                                                 String remarks,
                                                 MultipartFile afterPhoto,
                                                 UserDetails userDetails) {

        AssetRepair repair = assetRepairRepository.findById(repairId)
                .orElseThrow(() ->
                        new ResourceNotFound("Repair Not Found"));

        User technician = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFound("Technician Not Found"));

        if (!repair.getRepairedBy().getId().equals(technician.getId())) {
            throw new ResourceAlreadyExists("This repair is not assigned to you");
        }

        String photoPath = fileStorageService.uploadFile(afterPhoto);

        repair.setAfterPhoto(photoPath);
        repair.setRemarks(remarks);
        repair.setEndedAt(LocalDateTime.now());
        repair.setStatus(RepairStatus.FINISHED);

        AssetRepair savedRepair = assetRepairRepository.save(repair);

        Job job = repair.getJob();

        job.setStatus(JobStatus.COMPLETED);
        job.setEndTime(LocalDateTime.now());

        jobRepository.save(job);

        JobRequest jobRequest = job.getJobRequest();

        jobRequest.setStatus(JobRequestStatus.CLOSED);
        jobRequest.setUpdatedDate(LocalDateTime.now());

        jobRequestRepository.save(jobRequest);

        emailService.sendJobCompletionMail(
                jobRequest.getUser().getEmail());

        return assetRepairMapper.toDto(savedRepair);
    }

    public List<AssetRepairResponseDTO> allRepairs() {

        return assetRepairRepository.findAll()
                .stream()
                .map(assetRepairMapper::toDto)
                .toList();
    }
}