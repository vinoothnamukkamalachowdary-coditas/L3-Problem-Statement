package com.example.demo.FieldOps.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assetAttachments")
public class AssetAttachments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    private Assets asset;

    @NotNull
    private String fileName;

    @NotNull
    private String fileUrl;

    @NotNull
    private String fileType;

    @NotNull
    private String fileSize;

//    public void setAssetId(Long assetId) {
//    }
}
