package com.example.demo.FieldOps.Controller;

import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerAssetAttachmentsResponseDTO;
import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerAssetsResponseDTO;
import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerDashBoardResponseDTO;
import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerJobRequestsResponseDTO;
import com.example.demo.FieldOps.Service.CustomerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class CustomerDashboardController {

    private final CustomerDashboardService service;

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDashBoardResponseDTO> getCustomerDashboard(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "true") boolean ascending,
            @RequestParam(required = false, defaultValue = "") String search
    ){
        return ResponseEntity.ok(service.getCustomerDashboard(id,page,size,sortBy,ascending,search));
    }


    @GetMapping("/customer/{customerId}/assets")
    public ResponseEntity<List<CustomerAssetsResponseDTO>> getassetsByCustomerId(@PathVariable Long customerId){
        return ResponseEntity.ok(service.getassetsByCustomerId(customerId));
    }

    @GetMapping("/customer/assetAttachments/{attachmentId}")
    public ResponseEntity<List<CustomerAssetAttachmentsResponseDTO>> getassetAttachments(@PathVariable Long attachmentId){
        return ResponseEntity.ok(service.getassetAttachments(attachmentId));
    }

    @GetMapping("/customer/jobRequests/{customerId}")
    public ResponseEntity<List<CustomerJobRequestsResponseDTO>> getJobRequests(@PathVariable Long customerId){
        return ResponseEntity.ok(service.getJobRequests(customerId));
    }

}
