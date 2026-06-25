package com.example.demo.FieldOps.Repository;

import com.example.demo.FieldOps.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    boolean existsByJobId(Long jobId);
    Optional<Job> findByJobRequestId(Long jobRequestId);
}
