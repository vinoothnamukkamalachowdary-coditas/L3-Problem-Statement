package com.example.demo.FieldOps.Repository;

import com.example.demo.FieldOps.Constants.RepairStatus;
import com.example.demo.FieldOps.Entity.AssetRepair;
import com.example.demo.FieldOps.Entity.Job;
import com.example.demo.FieldOps.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepairRepository extends JpaRepository<AssetRepair, Long> {
    boolean existsByJob(Job job);

    Optional<AssetRepair> findByJob(Job job);

    Optional<AssetRepair> findByIdAndRepairedBy(Long id, User repairedBy);

    List<AssetRepair> findByRepairedBy(User repairedBy);

    List<AssetRepair> findByStatus(RepairStatus status);

}
