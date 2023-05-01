package com.br.auth.repository;

import com.br.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface AuthRepository extends JpaRepository<User, UUID> {
    User findByUserName(String username);

    User findByLogin(String login);
}


