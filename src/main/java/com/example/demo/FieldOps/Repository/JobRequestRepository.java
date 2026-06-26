package com.example.demo.FieldOps.Repository;

import com.example.demo.FieldOps.Entity.JobRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRequestRepository extends JpaRepository<JobRequest, Long> {

    @Query("SELECT j FROM JobRequest j WHERE j.user.id = :customerId")
    List<JobRequest> findByCustomerId(@Param("customerId") Long customerId);

}
