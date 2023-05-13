package com.br.library.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Table(name="author", schema = "public")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false)
    private UUID id;

    @Column(updatable = false)
    private String name;

    private int age;

    @ManyToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JoinTable(name = "author_book",joinColumns = {@JoinColumn(name="author_id")},
            inverseJoinColumns = {@JoinColumn(name="book_id")})
    private Set<Book> books;
}
