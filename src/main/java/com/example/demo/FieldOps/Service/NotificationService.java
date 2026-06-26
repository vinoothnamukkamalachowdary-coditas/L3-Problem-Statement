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
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;
    private final EmailService emailService;

    public NotificationResponseDTO sendNotification(
            Long jobId,
            NotificationRequestDTO dto,
            UserDetails auth) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFound("Job not found"));

        if (notificationRepository.findByJob(job).isPresent()) {
            throw new ResourceAlreadyExists(
                    "Notification already sent for this Job");
        }

        var sender = userRepository.findByEmail(auth.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFound("Logged-in user not found"));

        Notification notification = new Notification();

        notification.setJob(job);
        notification.setIssuedBy(sender);

        notification.setIssuedTo(dto.getIssuedTo());
        notification.setRecipientName(dto.getRecipientName());
        notification.setMessage(dto.getMessage());

        Notification savedNotification =
                notificationRepository.save(notification);

        emailService.sendJobAssignmentMail(dto.getIssuedTo());

        return notificationMapper.toNotificationResponseDTO(savedNotification);
    }
}
