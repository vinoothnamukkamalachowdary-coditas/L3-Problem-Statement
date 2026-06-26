package com.example.demo.FieldOps.Entity;

import com.example.demo.FieldOps.Constants.RepairStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AssetsRepairs")
public class AssetRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String beforePhoto;

    private String afterPhoto;

    @NotNull
    private String remarks;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    @Enumerated(EnumType.STRING)
    private RepairStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technician_id")
    private User repairedBy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    private Assets repairedAsset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;
}
