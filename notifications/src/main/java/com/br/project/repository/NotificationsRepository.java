package com.br.project.repository;

import com.br.project.model.NotificationsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationsRepository extends JpaRepository<NotificationsModel, UUID> {
}
