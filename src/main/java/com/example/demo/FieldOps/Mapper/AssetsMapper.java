package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Response.AssetsResponseDTO;
import com.example.demo.FieldOps.Entity.AssetAttachments;
import com.example.demo.FieldOps.Entity.Assets;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class AssetsMapper {
    public AssetsResponseDTO toResponse(Assets assets){
        AssetsResponseDTO ResponseDTO = new AssetsResponseDTO();
        ResponseDTO.setId(assets.getId());
        ResponseDTO.setAssetName(assets.getAssetName());
        ResponseDTO.setAssetType(assets.getAssetType());
        ResponseDTO.setAssetDescription(assets.getAssetDescription());
//        ResponseDTO.setFileName(assets.getAttachments().stream().map(AssetAttachments::f).collect(Collectors.toList()));
        ResponseDTO.setAssetCode(assets.getAssetCode());
        //ResponseDTO.setCustomerId(assets.getCustomer().getId());
        ResponseDTO.setCreatedDate(LocalDateTime.now());
        ResponseDTO.setUpdatedDate(LocalDateTime.now());
        return ResponseDTO;
    }
}
