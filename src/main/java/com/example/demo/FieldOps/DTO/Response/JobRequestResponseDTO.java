package com.example.demo.FieldOps.DTO.Response;

import com.example.demo.FieldOps.Constants.JobRequestStatus;
import com.example.demo.FieldOps.Entity.Assets;
import com.example.demo.FieldOps.Entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobRequestResponseDTO {
    private Long id;
    private String title;
    private String Notes;
    private JobRequestStatus status;
    private LocalDateTime createdDate;
    private Long assetId;
    private Long customerId;
    private LocalDateTime updatedDate;
}
