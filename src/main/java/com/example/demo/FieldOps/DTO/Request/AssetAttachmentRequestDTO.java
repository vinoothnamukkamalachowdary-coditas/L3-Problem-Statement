package com.example.demo.FieldOps.DTO.Request;

import lombok.Data;

@Data
public class AssetAttachmentRequestDTO {
    private Long assetId;
    private String fileName;
    private String fileUrl;
    private String fileSize;
    private String fileType;
}
