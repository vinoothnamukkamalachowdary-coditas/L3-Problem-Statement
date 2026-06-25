package com.example.demo.FieldOps.Controller;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job")
@RequiredArgsConstructor
public class JobController {

    private final JobRequestService jobRequestService;
    private final JobService jobService;
    private final NotificationService notificationService;

    @PostMapping("/raiseRequest")
    public ResponseEntity<JobRequestResponseDTO> raiseRequest(@Valid @RequestBody JobRequestRequestDTO jobRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(jobRequestService.raiseRequest(jobRequest));
    }

    @PostMapping("/assign/jobRequest/{id}")
    public ResponseEntity<JobResponseDTO> assign(@PathVariable Long id, @Valid @RequestBody JobRequestRequestDTO jobRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.assign(id,jobRequest));
    }

    @PostMapping("/sendNotification/forJob/{jobId}")
    public ResponseEntity<NotificationResponseDTO> sendNotification(@PathVariable Long jobId, @Valid @RequestBody NotificationRequestDTO notificationRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.sendNotification(jobId,notificationRequestDTO));
    }


}
