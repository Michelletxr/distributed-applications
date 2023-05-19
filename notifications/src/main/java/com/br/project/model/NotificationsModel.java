package com.br.project.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    public NotificationsModel(String emailFrom, String emailTo, String title, String text){
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.text = text;
        this.title = title;
    }
    public record NotificationDto(String emailFrom, String emailTo, String title, String text){}
}