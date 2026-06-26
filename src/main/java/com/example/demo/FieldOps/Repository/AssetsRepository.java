package com.example.demo.FieldOps.Repository;

import com.example.demo.FieldOps.Entity.Assets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetsRepository extends JpaRepository<Assets, Long> {
    boolean existsByAssetName(String assetName);
    List<Assets> findByCustomerId(String customerId);
}
