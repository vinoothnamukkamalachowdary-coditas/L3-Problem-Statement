package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.DTO.Request.AssetAttachmentRequestDTO;
import com.example.demo.FieldOps.DTO.Request.AssetUpdateRequestDTO;
import com.example.demo.FieldOps.DTO.Request.AssetsRequestDTO;
import com.example.demo.FieldOps.DTO.Response.AssetAttachmentResponseDTO;
import com.example.demo.FieldOps.DTO.Response.AssetsResponseDTO;
import com.example.demo.FieldOps.Entity.AssetAttachments;
import com.example.demo.FieldOps.Entity.Assets;
import com.example.demo.FieldOps.Exception.ResourceAlreadyExists;
import com.example.demo.FieldOps.Exception.ResourceNotFound;
import com.example.demo.FieldOps.Mapper.AssetAttachmentsMapper;
import com.example.demo.FieldOps.Mapper.AssetsMapper;
import com.example.demo.FieldOps.Repository.AssetAttachmentsRepository;
import com.example.demo.FieldOps.Repository.AssetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetsService {

    private final AssetsRepository assetsRepository;
    private final AssetAttachmentsRepository attachmentsRepository;
    private final AssetAttachmentsMapper attachmentsMapper;
    private final AssetsMapper mapper;

    public AssetsResponseDTO saveAsset(AssetsRequestDTO assets) {
        if (assetsRepository.existsByAssetName(assets.getAssetName())) {
            throw  new ResourceAlreadyExists("Asset with this Name Already Exists");
        }
        Assets asset1 = new Assets();
        asset1.setAssetName(assets.getAssetName());
        asset1.setAssetType(assets.getAssetType());
        asset1.setAssetDescription(assets.getAssetDescription());
        asset1.setCustomer(asset1.getCustomer());
        asset1.setActive(true);
        asset1.setCreatedDate(LocalDateTime.now());
//        asset1.setUpdatedDate(asset1.getUpdatedDate());
        asset1.setRequests(asset1.getRequests());
        return mapper.toResponse(assetsRepository.save(asset1));
    }

    public List<AssetsResponseDTO> getAllAssets() {
        return assetsRepository.findAll().stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    public AssetAttachmentResponseDTO addPhoto(AssetAttachmentRequestDTO assetsPhoto, Long id) {
        if (assetsPhoto != null) {
            AssetAttachments assetAttachments = attachmentsRepository.findById(id).orElse(null);
            if (assetAttachments != null) {
                throw new ResourceAlreadyExists("Photo with this Id Already Exists");
            }
        }
        if(attachmentsRepository.existsByAssetIdAndFileName(id, assetsPhoto.getFileName())) {
            throw  new ResourceAlreadyExists("Asset with this Name Already Exists");
        }
        AssetAttachments assetAttachments = new AssetAttachments();
        assetAttachments.setFileName(assetsPhoto.getFileName());
        assetAttachments.setFileType(assetsPhoto.getFileType());
        assetAttachments.setFileSize(assetsPhoto.getFileSize());
        assetAttachments.setFileUrl(assetsPhoto.getFileUrl());
        assetsPhoto.setAssetId(assetsPhoto.getAssetId());
        return attachmentsMapper.ResponseDTO(attachmentsRepository.save(assetAttachments));
    }

    public AssetsResponseDTO modifyAsset(AssetUpdateRequestDTO requestDTO, Long assetId) {
        Assets asset = assetsRepository.findById(assetId).orElseThrow(() -> new ResourceNotFound("Asset Is Not Found"));
        asset.setAssetName(requestDTO.getAssetName());
        asset.setAssetType(asset.getAssetType());
        asset.setAssetDescription(asset.getAssetDescription());
        asset.setAssetCode(requestDTO.getAssetDescription());
        asset.setAttachments(asset.getAttachments());
        return mapper.toResponse(assetsRepository.save(asset));
    }

    public String deleteAsset(Long id) {
        Assets assets = assetsRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Asset is Not Found with the id " + id));
        assets.setActive(false);
        assetsRepository.save(assets);
        return "User has been deleted";
    }

    public AssetsResponseDTO getAssetById(Long assetId) {
        return assetsRepository.findById(assetId).map(mapper::toResponse).orElseThrow(() -> new ResourceNotFound("Asset is Not Found with the id " + assetId));
    }
}
