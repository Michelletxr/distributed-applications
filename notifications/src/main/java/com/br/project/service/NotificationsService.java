package com.br.project.service;

import com.br.project.model.NotificationsModel;
import com.br.project.repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationsService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private NotificationsRepository repository;
    public Boolean sendEmail(NotificationsModel msg) {

        NotificationsModel email = new NotificationsModel();
        email.setSendDateEmail(LocalDateTime.now());
        Boolean send = null;

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getTitle());
            message.setText(email.getText());
            emailSender.send(message);
            email.setSendEmail(true);
            repository.save(email);
            send = true;
        } catch (MailException e){
            send = false;
            email.setSendEmail(false);
        }

        return send;
    }
}
