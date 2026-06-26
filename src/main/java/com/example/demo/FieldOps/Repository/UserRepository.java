package com.example.demo.FieldOps.Repository;

import com.example.demo.FieldOps.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    /**
     * Fetches a paginated and filtered list of user activities or user records for the dashboard.
     * Searches dynamically across the user's name
     */
    // Removed the username condition completely
    @Query("SELECT u FROM User u WHERE u.id = :id AND (:search IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<User> findActivitiesByUserId(
            @Param("id") Long id,
            @Param("search") String search,
            Pageable pageable
    );


}
