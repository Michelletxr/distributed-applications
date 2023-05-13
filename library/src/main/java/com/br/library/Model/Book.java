package com.br.library.Model;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "author_book", joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name="author_id")})
    private Set<Author> author;
}
