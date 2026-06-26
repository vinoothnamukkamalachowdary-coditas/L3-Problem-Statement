package com.example.demo.FieldOps.Repository;

import com.example.demo.FieldOps.Entity.Job;
import com.example.demo.FieldOps.Entity.JobRequest;
import com.example.demo.FieldOps.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByJobRequest(JobRequest jobRequest);
    Optional<Job> findByIdAndTechnician(Long id, User technician);
}
