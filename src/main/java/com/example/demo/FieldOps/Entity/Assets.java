package com.example.demo.FieldOps.Entity;

import com.example.demo.FieldOps.Constants.AssetType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assets")
public class Assets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String assetName;

    @NotNull
    private String assetDescription;

    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    private boolean isActive;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @OneToMany(mappedBy = "asset")
    private List<AssetAttachments> attachments;

    @OneToMany(mappedBy = "asset")
    private List<JobRequest> requests;
}
