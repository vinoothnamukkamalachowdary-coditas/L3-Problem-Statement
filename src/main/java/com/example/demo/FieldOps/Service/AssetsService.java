package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.DTO.Request.AssetUpdateRequestDTO;
import com.example.demo.FieldOps.DTO.Request.AssetsRequestDTO;
import com.example.demo.FieldOps.DTO.Response.AssetAttachmentResponseDTO;
import com.example.demo.FieldOps.DTO.Response.AssetsResponseDTO;
import com.example.demo.FieldOps.Entity.AssetAttachments;
import com.example.demo.FieldOps.Entity.Assets;
import com.example.demo.FieldOps.Entity.User;
import com.example.demo.FieldOps.Exception.ResourceAlreadyExists;
import com.example.demo.FieldOps.Exception.ResourceNotFound;
import com.example.demo.FieldOps.Mapper.AssetAttachmentsMapper;
import com.example.demo.FieldOps.Mapper.AssetsMapper;
import com.example.demo.FieldOps.Repository.AssetAttachmentsRepository;
import com.example.demo.FieldOps.Repository.AssetsRepository;
import com.example.demo.FieldOps.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetsService {

    private final AssetsRepository assetsRepository;
    private final AssetAttachmentsRepository attachmentRepository;
    private final AssetAttachmentsMapper attachmentMapper;
    private final AssetsMapper assetsMapper;
    private final FileStorageService fileStorageService;
    private final UserRepository userRepository;

    /**
     * Create Asset
     */
    public AssetsResponseDTO saveAsset(AssetsRequestDTO dto) {

        if (assetsRepository.existsByAssetName(dto.getAssetName())) {
            throw new ResourceAlreadyExists(
                    "Asset already exists with name : " + dto.getAssetName());
        }

        User customer = userRepository.findById(dto.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Customer not found with id : " + dto.getCustomerId()));

        Assets asset = new Assets();

        asset.setAssetName(dto.getAssetName());
        asset.setAssetDescription(dto.getAssetDescription());
        asset.setAssetCode(dto.getAssetCode());
        asset.setAssetType(dto.getAssetType());

        // sets customer
        asset.setCustomer(customer);

        asset.setActive(true);
        asset.setCreatedDate(LocalDateTime.now());
        asset.setUpdatedDate(LocalDateTime.now());

        Assets savedAsset = assetsRepository.save(asset);

        return assetsMapper.toDto(savedAsset);
    }

    /**
     * Get all Assets
     */
    @Transactional(readOnly = true)
    public List<AssetsResponseDTO> getAllAssets() {

        return assetsRepository.findAll()
                .stream()
                .map(assetsMapper::toDto)
                .toList();
    }

    /**
     * Get Asset by Id
     */
    @Transactional(readOnly = true)
    public AssetsResponseDTO getAssetById(Long assetId) {

        Assets asset = assetsRepository.findById(assetId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Asset not found : " + assetId));

        return assetsMapper.toDto(asset);
    }

    /**
     * Upload Asset Photo
     */
    public AssetAttachmentResponseDTO addPhoto(
            MultipartFile file,
            Long assetId) {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Please upload a file.");
        }

        Assets asset = assetsRepository.findById(assetId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Asset not found : " + assetId));

        String fileUrl = fileStorageService.uploadFile(file);

        AssetAttachments attachment = new AssetAttachments();

        attachment.setAsset(asset);

        attachment.setFileName(file.getOriginalFilename());

        attachment.setFileType(file.getContentType());

        attachment.setFileUrl(fileUrl);

        attachment.setFileSize(String.valueOf(file.getSize()));

        AssetAttachments savedAttachment =
                attachmentRepository.save(attachment);

        return attachmentMapper.toResponseDto(savedAttachment);
    }

    /**
     * Update Asset
     */
    public AssetsResponseDTO modifyAsset(
            AssetUpdateRequestDTO dto,
            Long assetId) {

        Assets asset = assetsRepository.findById(assetId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Asset not found : " + assetId));

        asset.setAssetName(dto.getAssetName());

        asset.setAssetType(dto.getAssetType());

        asset.setAssetDescription(dto.getAssetDescription());

        asset.setUpdatedDate(LocalDateTime.now());

        Assets updatedAsset =
                assetsRepository.save(asset);

        return assetsMapper.toDto(updatedAsset);
    }

    /**
     * Soft Delete
     */
    public String deleteAsset(Long assetId) {

        Assets asset = assetsRepository.findById(assetId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Asset not found : " + assetId));

        asset.setActive(false);

        asset.setUpdatedDate(LocalDateTime.now());

        assetsRepository.save(asset);

        return "Asset deleted successfully.";
    }
}
