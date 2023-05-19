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
}
