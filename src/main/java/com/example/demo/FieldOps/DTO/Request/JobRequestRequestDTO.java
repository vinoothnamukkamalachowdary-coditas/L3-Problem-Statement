package com.example.demo.FieldOps.DTO.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobRequestRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String notes;

    @NotNull
    private Long assetId;

    @NotNull
    private Long customerId;
}
