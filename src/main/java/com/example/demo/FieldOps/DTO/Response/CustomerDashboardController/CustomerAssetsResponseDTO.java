package com.example.demo.FieldOps.DTO.Response.CustomerDashboardController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAssetsResponseDTO {
    private Long id;
    private Long assetId;
    private String assetName;
    private String assetDescription;
    private String assetType;
    private String assetCode;
}
