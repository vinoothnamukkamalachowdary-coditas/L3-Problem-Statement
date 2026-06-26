package com.example.demo.FieldOps.Mapper;

import com.example.demo.FieldOps.DTO.Request.AssetsRequestDTO;
import com.example.demo.FieldOps.DTO.Response.AssetsResponseDTO;
import com.example.demo.FieldOps.Entity.AssetAttachments;
import com.example.demo.FieldOps.Entity.Assets;
import com.example.demo.FieldOps.Entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AssetsMapper {
    public Assets toEntity(AssetsRequestDTO dto,
                           User customer) {

        Assets asset = new Assets();

        asset.setAssetName(dto.getAssetName().trim());

        asset.setAssetDescription(
                dto.getAssetDescription().trim());

        asset.setAssetType(dto.getAssetType());

        asset.setAssetCode(dto.getAssetCode());

        asset.setCustomer(customer);

        asset.setActive(true);

        asset.setCreatedDate(LocalDateTime.now());

        asset.setUpdatedDate(LocalDateTime.now());

        return asset;
    }

    public AssetsResponseDTO toDto(Assets asset) {

        return AssetsResponseDTO.builder()
                .id(asset.getId())
                .assetName(asset.getAssetName())
                .assetDescription(asset.getAssetDescription())
                .assetCode(asset.getAssetCode())
                .assetType(asset.getAssetType())
                .active(asset.isActive())
                .customerId(asset.getCustomer().getId())
                .createdDate(asset.getCreatedDate())
                .updatedDate(asset.getUpdatedDate())
                .build();
    }
}
