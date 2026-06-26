package com.example.demo.FieldOps.DTO.Response;

import com.example.demo.FieldOps.Constants.AssetType;
import com.example.demo.FieldOps.Entity.AssetAttachments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetsResponseDTO {
    private Long id;
    private String assetName;
    private String assetDescription;
    private AssetType assetType;
    private String assetCode;
    private Long customerId;
    private boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
//    private List<AssetAttachments> attachments;
}
