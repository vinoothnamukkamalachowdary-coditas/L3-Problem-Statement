package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.AssetAttachmentResponseDTO;
import com.example.demo.FieldOps.Entity.AssetAttachments;
import org.springframework.stereotype.Component;

@Component
public class AssetAttachmentsMapper {
    public AssetAttachmentResponseDTO ResponseDTO(AssetAttachments assetAttachments){
        AssetAttachmentResponseDTO assetAttachmentResponseDTO = new AssetAttachmentResponseDTO();
        assetAttachmentResponseDTO.setId(assetAttachments.getId());
        assetAttachmentResponseDTO.setAssetId(assetAttachments.getAssetId().getId());
        assetAttachmentResponseDTO.setFileName(assetAttachments.getFileName());
        assetAttachmentResponseDTO.setFileSize(assetAttachments.getFileSize());
        assetAttachmentResponseDTO.setFileType(assetAttachments.getFileType());
        assetAttachmentResponseDTO.setFileUrl(assetAttachments.getFileUrl());
        return  assetAttachmentResponseDTO;
    }
}
