package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.AssetAttachmentResponseDTO;
import com.example.demo.FieldOps.Entity.AssetAttachments;
import org.springframework.stereotype.Component;

@Component
public class AssetAttachmentsMapper {
    public AssetAttachmentResponseDTO toResponseDto(AssetAttachments attachment) {

        if (attachment == null) {
            return null;
        }

        AssetAttachmentResponseDTO response = new AssetAttachmentResponseDTO();

        response.setId(attachment.getId());

        // Set Asset Id if asset is present
        if (attachment.getAsset() != null) {
            response.setAssetId(attachment.getAsset().getId());
        }

        response.setFileName(attachment.getFileName());
        response.setFileUrl(attachment.getFileUrl());
        response.setFileType(attachment.getFileType());
        response.setFileSize(attachment.getFileSize());

        return response;
    }
}
