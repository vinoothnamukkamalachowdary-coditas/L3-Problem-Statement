package com.example.demo.FieldOps.DTO.Request;

import com.example.demo.FieldOps.Constants.AssetType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssetUpdateRequestDTO {
    private String assetName;
    private String assetDescription;
    private AssetType assetType;
    private LocalDateTime updatedDate;
    private boolean isActive;
}
