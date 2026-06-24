package com.example.demo.FieldOps.DTO.Request;

import com.example.demo.FieldOps.Constants.AssetType;
import com.example.demo.FieldOps.Entity.AssetAttachments;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AssetsRequestDTO {
    private String assetName;
    private String assetDescription;
    private AssetType assetType;
    private boolean isActive;
    private List<AssetAttachments> attachments;
}
