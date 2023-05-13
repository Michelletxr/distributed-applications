package com.br.library.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false)
    private UUID id;

    @Column(updatable = false)
    private String name;

    private int age;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
