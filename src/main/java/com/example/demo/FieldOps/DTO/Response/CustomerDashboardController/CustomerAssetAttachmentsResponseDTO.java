package com.example.demo.FieldOps.DTO.Response.CustomerDashboardController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAssetAttachmentsResponseDTO {

    private Long attachmentId;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private String fileSize;
}
