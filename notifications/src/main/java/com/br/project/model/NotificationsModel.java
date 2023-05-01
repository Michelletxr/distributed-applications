package com.br.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class NotificationsModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NonNull
    private String username;
    @NonNull
    @Email
    private String emailFrom;
    @NonNull
    @Email
    private String emailTo;
    @NonNull
    private String title;
    private String text;
    private LocalDateTime sendDateEmail;
    private Boolean sendEmail;

    @Builder
    public void NotificationsModel(String emailFrom, String emailTo,
                                                 String title, String text, boolean sendEmail){
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.text = text;
        this.title = title;
        this.sendEmail = sendEmail;
    }
}