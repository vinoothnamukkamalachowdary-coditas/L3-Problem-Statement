package com.example.demo.FieldOps.DTO.Response.CustomerDashboardController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerJobRequestsResponseDTO {
    private Long id;
    private Long jobRequestId;
    private Long assetId;
    private String title;
    private String notes;
}
