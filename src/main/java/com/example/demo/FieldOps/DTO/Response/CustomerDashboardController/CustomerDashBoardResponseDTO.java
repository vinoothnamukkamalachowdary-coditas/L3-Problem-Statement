package com.example.demo.FieldOps.DTO.Response.CustomerDashboardController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDashBoardResponseDTO {
    private Long id;
    private Long customerId;
    private String customerName;
    private Integer totalAssets;
    private Integer totalAssetAttachments;
    private Integer totalJobRequests;
    private List<CustomerAssetsResponseDTO> customerAssets;
    private List<CustomerJobRequestsResponseDTO> customerJobRequests;
    private List<CustomerAssetAttachmentsResponseDTO> customerassetAttachments;
}
