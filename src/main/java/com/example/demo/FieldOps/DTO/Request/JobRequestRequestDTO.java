package com.example.demo.FieldOps.DTO.Request;

import com.example.demo.FieldOps.Constants.JobRequestStatus;
import com.example.demo.FieldOps.Entity.Assets;
import com.example.demo.FieldOps.Entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobRequestRequestDTO {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String Notes;
    private Long assetId;
    private Long customerId;
}
