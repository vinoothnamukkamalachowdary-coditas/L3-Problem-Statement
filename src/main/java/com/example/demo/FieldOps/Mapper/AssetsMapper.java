package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.AssetsResponseDTO;
import com.example.demo.FieldOps.Entity.Assets;
import org.springframework.stereotype.Component;

@Component
public class AssetsMapper {
    public AssetsResponseDTO toResponse(Assets assets){
        AssetsResponseDTO ResponseDTO = new AssetsResponseDTO();
        ResponseDTO.setId(assets.getId());
        ResponseDTO.setAssetName(assets.getAssetName());
        ResponseDTO.setAssetType(assets.getAssetType());
        ResponseDTO.setAssetDescription(assets.getAssetDescription());
        ResponseDTO.setAttachments(assets.getAttachments());
        ResponseDTO.setCreatedDate(assets.getCreatedDate());
        ResponseDTO.setUpdatedDate(assets.getUpdatedDate());
        return ResponseDTO;
    }
}
