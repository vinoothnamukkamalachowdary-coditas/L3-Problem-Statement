package com.example.demo.FieldOps.Controller;

import com.example.demo.FieldOps.DTO.Response.AssetRepairResponseDTO;
import com.example.demo.FieldOps.DTO.Response.JobResponseDTO;
import com.example.demo.FieldOps.Service.AssetRepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repairs")
@RequiredArgsConstructor
public class AssetRepairController {

    private final AssetRepairService repairService;

    /**
     * Technician accepts assigned job
     */
    @PreAuthorize("hasRole('TECHNICIAN')")
    @PutMapping("/accept/{jobId}")
    public ResponseEntity<JobResponseDTO> acceptJob(
            @PathVariable Long jobId,
            @AuthenticationPrincipal UserDetails userDetails){

        return ResponseEntity.ok(
                repairService.acceptJob(jobId,userDetails));
    }

    /**
     * Technician starts repair
     */
    @PreAuthorize("hasRole('TECHNICIAN')")
    @PostMapping(value="/start/{jobId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AssetRepairResponseDTO> startRepair(

            @PathVariable Long jobId,

            @RequestParam("remarks") String remarks,

            @RequestParam("beforePhoto") MultipartFile beforePhoto,

            @AuthenticationPrincipal UserDetails userDetails){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repairService.startRepair(
                        jobId,
                        remarks,
                        beforePhoto,
                        userDetails));
    }

    /**
     * Technician completes repair
     */
    @PreAuthorize("hasRole('TECHNICIAN')")
    @PutMapping(value="/complete/{repairId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AssetRepairResponseDTO> completeRepair(

            @PathVariable Long repairId,

            @RequestParam("remarks") String remarks,

            @RequestParam("afterPhoto") MultipartFile afterPhoto,

            @AuthenticationPrincipal UserDetails userDetails){

        return ResponseEntity.ok(
                repairService.completeRepair(
                        repairId,
                        remarks,
                        afterPhoto,
                        userDetails));
    }

    /**
     * repair history track for technician and dispatcher
     */
    @PreAuthorize("hasAnyRole('TECHNICIAN','DISPATCHER')")
    @GetMapping
    public ResponseEntity<List<AssetRepairResponseDTO>> allRepairs(){

        return ResponseEntity.ok(
                repairService.allRepairs());
    }
}