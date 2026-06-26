package com.example.demo.FieldOps.Controller;

import com.example.demo.FieldOps.DTO.Request.JobRequestDTO;
import com.example.demo.FieldOps.DTO.Request.JobRequestRequestDTO;
import com.example.demo.FieldOps.DTO.Request.NotificationRequestDTO;
import com.example.demo.FieldOps.DTO.Response.JobRequestResponseDTO;
import com.example.demo.FieldOps.DTO.Response.JobResponseDTO;
import com.example.demo.FieldOps.DTO.Response.NotificationResponseDTO;
import com.example.demo.FieldOps.Service.JobRequestService;
import com.example.demo.FieldOps.Service.JobService;
import com.example.demo.FieldOps.Service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
@RequiredArgsConstructor
public class JobController {

    private final JobRequestService jobRequestService;
    private final JobService jobService;
    private final NotificationService notificationService;

    // CUSTOMER raises a service request
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/requests")
    public ResponseEntity<JobRequestResponseDTO> raiseRequest(
            @Valid @RequestBody JobRequestRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobRequestService.raiseRequest(requestDTO));
    }

    // CUSTOMER & DISPATCHER can view requests
    @PreAuthorize("hasAnyRole('CUSTOMER','DISPATCHER')")
    @GetMapping("/requests")
    public ResponseEntity<List<JobRequestResponseDTO>> allJobRequests() {

        return ResponseEntity.ok(jobRequestService.allJobRequests());
    }

    // Dispatcher assigns technician
    @PreAuthorize("hasRole('DISPATCHER')")
    @PostMapping("/{requestId}/assign")
    public ResponseEntity<JobResponseDTO> assignJob(
            @PathVariable Long requestId,
            @Valid @RequestBody JobRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobService.assign(requestId, dto));
    }

    // Dispatcher sends notification
    @PreAuthorize("hasRole('DISPATCHER')")
    @PostMapping("/{jobId}/notifications")
    public ResponseEntity<NotificationResponseDTO> sendNotification(
            @PathVariable Long jobId,
            @Valid @RequestBody NotificationRequestDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(
                notificationService.sendNotification(jobId, dto, userDetails));
    }

    // Dispatcher & Technician
    @PreAuthorize("hasAnyRole('DISPATCHER','TECHNICIAN')")
    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> allJobs() {

        return ResponseEntity.ok(jobService.allJobs());
    }


}
