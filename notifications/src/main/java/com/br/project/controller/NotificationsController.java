package com.br.project.controller;

import com.br.project.model.NotificationsModel;
import com.br.project.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/notifications")
public class NotificationsController {
    @Autowired
    NotificationsService service;

    @PostMapping(value = "send-email")
    public ResponseEntity<?> createEmail(@RequestBody @Valid NotificationsModel.NotificationDto msg) {
        ResponseEntity<?> response;
        Boolean send = service.sendEmail(msg);
        if (send) {
            response = new ResponseEntity<>("email enviado com sucesso!", HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>("erro ao tentar enviar email!", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}



