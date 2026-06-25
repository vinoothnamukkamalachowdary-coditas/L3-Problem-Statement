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

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/raiseRequest")
    public ResponseEntity<JobRequestResponseDTO> raiseRequest(@Valid @RequestBody JobRequestRequestDTO jobRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(jobRequestService.raiseRequest(jobRequest));
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_DISPATCHER')")
    @GetMapping("/request/all")
    public ResponseEntity<List<JobRequestResponseDTO>> allJobRequests(){
        return ResponseEntity.ok(jobRequestService.allJobRequests());
    }

    @PreAuthorize("hasRole('ROLE_DISPATCHER')")
    @PostMapping("/assign/jobRequest/{id}")
    public ResponseEntity<JobResponseDTO> assign(@PathVariable Long id, @Valid @RequestBody JobRequestDTO jobRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.assign(id,jobRequest));
    }

    @PreAuthorize("hasRole('DISPATCHER')")
    @PostMapping("/sendNotification/forJob/{jobId}")
    public ResponseEntity<NotificationResponseDTO> sendNotification(@PathVariable Long jobId, @Valid @RequestBody NotificationRequestDTO notificationRequestDTO,@AuthenticationPrincipal UserDetails auth){
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.sendNotification(jobId,notificationRequestDTO,auth));
    }

    @PreAuthorize("hasRole('ROLE_DISPATCHER','ROLE_TECHNICIAN')")
    @GetMapping("/allJobs")
    public ResponseEntity<List<JobResponseDTO>> allJobs(){
        return ResponseEntity.ok(jobService.allJobs());
    }

}
