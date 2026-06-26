package com.example.demo.FieldOps.DTO.Response;

import lombok.Data;

@Data
public class AssetAttachmentResponseDTO {

    private Long id;

    private Long assetId;

    private String fileName;

    private String fileUrl;

    private String fileType;

    private String fileSize;
}
