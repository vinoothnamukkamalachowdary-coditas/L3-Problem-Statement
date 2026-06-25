package com.example.demo.FieldOps.DTO.Request;

import com.example.demo.FieldOps.Constants.JobRequestStatus;
import com.example.demo.FieldOps.Entity.Assets;
import com.example.demo.FieldOps.Entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobRequestRequestDTO {
    @NotNull
    private String title;
    @NotNull
    private String Notes;
    private Assets assets;
    private User createdBy;
}
