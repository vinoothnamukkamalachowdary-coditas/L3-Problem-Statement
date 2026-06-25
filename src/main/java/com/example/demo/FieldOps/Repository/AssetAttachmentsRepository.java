package com.example.demo.FieldOps.Repository;

import com.example.demo.FieldOps.Entity.AssetAttachments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetAttachmentsRepository extends JpaRepository<AssetAttachments, Long> {
    List<AssetAttachments> findByAssetId(Long assetId);
    boolean existsByAssetIdAndFileName(Long assetId, String fileName);
}
