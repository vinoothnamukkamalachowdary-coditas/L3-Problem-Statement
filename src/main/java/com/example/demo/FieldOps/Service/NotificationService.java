package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.DTO.Request.NotificationRequestDTO;
import com.example.demo.FieldOps.DTO.Response.NotificationResponseDTO;
import com.example.demo.FieldOps.Entity.Job;
import com.example.demo.FieldOps.Entity.Notification;
import com.example.demo.FieldOps.Exception.ResourceAlreadyExists;
import com.example.demo.FieldOps.Exception.ResourceNotFound;
import com.example.demo.FieldOps.Mapper.NotificationMapper;
import com.example.demo.FieldOps.Repository.JobRepository;
import com.example.demo.FieldOps.Repository.NotificationRepository;
import com.example.demo.FieldOps.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    private final NotificationMapper notificationMapper;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public NotificationResponseDTO sendNotification(Long jobId, @Valid NotificationRequestDTO notificationRequestDTO, UserDetails auth) {
        if(notificationRepository.findById(jobId).isPresent()){
            throw new ResourceAlreadyExists("Notification already sent");
        }
        if(userRepository.findByEmail(emailService.jobAssignmentNotification(auth.getUsername())).isPresent()){
            throw new ResourceNotFound("User not found");
        }
        Notification notification = new Notification();
        Job job = jobRepository.findById(jobId).get();
        notification.setJob(job);
        notification.setMessage(notificationRequestDTO.getMessage());
        notification.setIssuedBy(notification.getIssuedBy());
        notification.setIssuedTo(notificationRequestDTO.getIssuedTo());
        notification.setRecipientName(notificationRequestDTO.getRecipientName());
        return notificationMapper.toNotificationResponseDTO(notificationRepository.save(notification));
    }
}
