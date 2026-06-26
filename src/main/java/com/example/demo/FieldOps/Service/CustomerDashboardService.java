package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerAssetAttachmentsResponseDTO;
import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerAssetsResponseDTO;
import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerDashBoardResponseDTO;
import com.example.demo.FieldOps.DTO.Response.CustomerDashboardController.CustomerJobRequestsResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDashboardService {
    public List<CustomerJobRequestsResponseDTO> getJobRequests(Long customerId) {
    }

    public List<CustomerAssetAttachmentsResponseDTO> getassetAttachments(Long attachmentId) {
    }

    public CustomerDashBoardResponseDTO getCustomerDashboard(int page, int size, String sortBy, boolean ascending, String search) {
    }

    public List<CustomerAssetsResponseDTO> getassetsByCustomerId(Long id) {
    }
}
