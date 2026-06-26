package com.example.demo.FieldOps.DTO.Response;

import com.example.demo.FieldOps.Constants.RepairStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetRepairResponseDTO {
    private Long id;

    private Long technicianId;

    private Long assetId;

    private Long jobId;

    private String beforePhoto;

    private String afterPhoto;

    private String remarks;

    private RepairStatus status;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
