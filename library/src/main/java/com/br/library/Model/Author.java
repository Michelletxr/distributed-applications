package com.br.library.Model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "books")
@EqualsAndHashCode(exclude="books")
@Table(name="author", schema = "public")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false)
    private UUID id;
    @Column(updatable = false)
    private String name;
    private int age;
    @OneToMany(mappedBy="author")
    private Set<Book> books;
/*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author_book",joinColumns = {@JoinColumn(name="author_id")},
            inverseJoinColumns = {@JoinColumn(name="book_id")})
    private Set<Book> books;*
 */
}
