package com.example.demo.FieldOps.Entity;

import com.example.demo.FieldOps.Constants.JobRequestStatus;
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
@Table(name = "jobRequest")
public class JobRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private JobRequestStatus status;

    @NotNull
    private String title;

    @NotNull
    private String notes;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_ID")
    private Assets asset;

    @OneToOne(mappedBy = "jobRequest")
    private Job job;


    public void setAsset(Long id) {

    }
}
