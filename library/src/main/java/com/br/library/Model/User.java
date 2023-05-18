package com.br.library.Model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name="user", schema = "public")
public class User {
    @Id
    @Column(unique = true, updatable = false)
    private UUID id;
    private String userName;
    private String email;

    public User(UUID id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
}
