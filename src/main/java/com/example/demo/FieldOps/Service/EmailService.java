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

    public void sendJobAssignmentMail(String email) {

            String message = """
                Dear Technician,

                A new job has been assigned to you.

                Please login to the Field Operations Portal and start working on it.

                Thank you.
                """;

            sendPlainEmail(email,
                    "New Job Assigned",
                    message);
        }

        public void sendJobCompletionMail(String email) {

            String message = """
                Dear Customer,

                Your asset repair has been completed successfully.

                Thank you for using our platform.
                """;

            sendPlainEmail(email,
                    "Asset Repair Completed",
                    message);
        }

        private void sendPlainEmail(String email,
                                    String subject,
                                    String body) {

            try {

                SimpleMailMessage mail = new SimpleMailMessage();

                String sender = "vinoothnamukkamala@gmail.com";
                mail.setFrom(sender);
                mail.setTo(email);
                mail.setSubject(subject);
                mail.setText(body);

                mailSender.send(mail);

            } catch (MailException ex) {

                throw new EmailSendingFailure(
                        "Unable to send email to " + email);
            }
        }

    }