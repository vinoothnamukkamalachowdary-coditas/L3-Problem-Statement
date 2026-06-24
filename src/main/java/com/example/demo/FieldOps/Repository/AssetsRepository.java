package com.example.demo.FieldOps.Repository;

import com.example.demo.FieldOps.Entity.Assets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetsRepository extends JpaRepository<Assets, Long> {
    boolean existsByAssetName(String assetName);
}
