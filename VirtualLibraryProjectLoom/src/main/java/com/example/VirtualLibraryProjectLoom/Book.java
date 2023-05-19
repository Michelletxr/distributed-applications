package com.example.VirtualLibraryProjectLoom;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="book", schema = "public")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false)
    private UUID id;

    @Column(updatable = false)
    private String name;

    private String genre;

    private int numberPages;

    private float rating;

    private UUID user_id;

}
