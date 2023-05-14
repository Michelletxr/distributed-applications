package com.br.library.Model;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@ToString(exclude = "author")
@EqualsAndHashCode(exclude="author")
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

    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinTable( name = "author_book", joinColumns = {@JoinColumn(name="book_id")},
           // inverseJoinColumns = {@JoinColumn(name="author_id")})
    @JoinColumn(name = "author_id", nullable = true)
    private Author author;
}
