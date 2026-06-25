package com.example.demo.FieldOps.Repository;

import com.example.demo.FieldOps.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
