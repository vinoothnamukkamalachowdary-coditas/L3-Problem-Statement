package com.example.demo.FieldOps.DTO.Request;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobRequestDTO {
    @NotNull
    private Long dispatcherId;

    @NotNull
    private Long technicianId;

}
