package com.example.demo.FieldOps.Repository;

import com.example.demo.FieldOps.Entity.JobRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRequestRepository extends JpaRepository<JobRequest, Long> {
    boolean existsByJobRequestId(Long jobRequestId);
}
