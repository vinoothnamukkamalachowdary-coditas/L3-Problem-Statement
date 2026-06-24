package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.DTO.Request.AssetsRequestDTO;
import com.example.demo.FieldOps.DTO.Response.AssetsResponseDTO;
import com.example.demo.FieldOps.Entity.Assets;
import com.example.demo.FieldOps.Exception.ResourceAlreadyExists;
import com.example.demo.FieldOps.Mapper.AssetsMapper;
import com.example.demo.FieldOps.Repository.AssetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetsService {
    private final AssetsRepository assetsRepository;
    private final AssetsMapper mapper;

    public AssetsResponseDTO saveAsset(AssetsRequestDTO assets) {
        if (assetsRepository.existsByAssetName(assets.getAssetName())) {
            throw  new ResourceAlreadyExists("Asset with this Name Already Exists");
        }
        Assets asset1 = new Assets();
        asset1.setAssetName(assets.getAssetName());
        asset1.setAssetType(assets.getAssetType());
        asset1.setAssetDescription(assets.getAssetDescription());
        asset1.setAttachments(assets.getAttachments());
        asset1.setCustomer(asset1.getCustomer());
        asset1.setActive(true);
        asset1.setCreatedDate(asset1.getCreatedDate());
        asset1.setUpdatedDate(asset1.getUpdatedDate());
        asset1.setRequests(asset1.getRequests());
        return mapper.toResponse(assetsRepository.save(asset1));
    }

    public List<AssetsResponseDTO> getAllAssets() {
        return assetsRepository.findAll().stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }
}
