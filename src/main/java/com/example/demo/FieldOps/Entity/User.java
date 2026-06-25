package com.example.demo.FieldOps.Entity;

import com.example.demo.FieldOps.Constants.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(unique = true)
    @NotBlank
    @Email
    private String email;

    @Column(unique = true)
    @NotBlank
    private String password;

    @NotNull
    private String Address;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private List<JobRequest>  jobRequests;

    @OneToMany(mappedBy = "dispatcherId")
    private List<Job> dispatchedJobs;

    @OneToMany(mappedBy = "technicianId")
    private List<Job> assignedJobs;

    @OneToMany(mappedBy = "customer")
    private List<Assets> assets;

    @OneToMany(mappedBy = "repairedBy")
    private List<AssetRepair> assetRepairs;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
