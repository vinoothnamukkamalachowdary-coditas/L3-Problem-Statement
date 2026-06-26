package com.example.demo.FieldOps.DTO.Request;

import com.example.demo.FieldOps.Constants.AssetType;
import com.example.demo.FieldOps.Entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AssetsRequestDTO {

    private String assetName;
    private String assetDescription;
    private String assetCode;
    private AssetType assetType;
    private Long customerId;

}
