package com.example.demo.FieldOps.Service;

import com.example.demo.FieldOps.Exception.EmailSendingFailure;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String jobAssignmentNotification(String emailId) {

        String JobRequestToken = UUID.randomUUID().toString();

        String notificationMessage =
                """
                Dear Technician,this mail is sent to you to know that You have been assigned the Job
                with the below JobRequestToken.Kindly do the task
                Token:
                """
                        + JobRequestToken;

        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(emailId);
            mailMessage.setSubject("Tenant User Invitation");
            mailMessage.setText(notificationMessage);

            mailSender.send(mailMessage);

        } catch (MailException ex) {

            throw new EmailSendingFailure(
                    "Unable to send invitation email"
            );
        }

        return JobRequestToken;
    }

    public void sendJobCompletionMail(String emailId) {
        String message =
                        "Hi "
                        + "Your repair is successfully finished for your asset "
                        + "You can track your status from our platform,"
                        + "Thank you for choosing us.";

        sendPlainEmail(emailId, "Asset Repair Confirmation", message);
    }
    private void sendPlainEmail(String emailId, String subject, String body) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailId);
            mailMessage.setSubject(subject);
            mailMessage.setText(body);
            mailSender.send(mailMessage);
        } catch (MailException ex) {
            throw new EmailSendingFailure("Unable to send email: " +emailId);
        }
    }
}
