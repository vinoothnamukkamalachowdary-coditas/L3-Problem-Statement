package com.example.demo.FieldOps.Entity;

import com.example.demo.FieldOps.Constants.JobStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.proxy.Dispatcher;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobRequestId")
    private JobRequest jobRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignedBy")
    private User dispatcher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignedTo")
    private User technician;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime endTime;

    @OneToMany(mappedBy = "job")
    private List<AssetRepair> repairs;

    @OneToMany(mappedBy = "job")
    private List<Notification> notifications;

}
