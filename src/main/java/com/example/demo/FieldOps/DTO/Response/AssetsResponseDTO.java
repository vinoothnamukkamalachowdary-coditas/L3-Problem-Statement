package com.example.demo.FieldOps.DTO.Response;

import com.example.demo.FieldOps.Constants.AssetType;
import com.example.demo.FieldOps.Entity.AssetAttachments;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AssetsResponseDTO {
    private Long id;
    private String assetName;
    private String assetDescription;
    private AssetType assetType;
    private String assetCode;
    private Long customerId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<AssetAttachments> assetAttachments;
}
