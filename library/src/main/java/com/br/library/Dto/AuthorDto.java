package com.br.library.Dto;

import com.br.library.Model.Author;
import com.br.library.Model.Book;
import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthorDto {

    private UUID id;

    private String name;

    private int age;

    private List<UUID> books = new ArrayList<>();

    public AuthorDto(Author author){
        this.name = author.getName();
        this.age = author.getAge();
        this.id = author.getId();
        author.getBooks().forEach(book -> this.books.add(book.getId()));
    }

    public Author buildAuthorDtoToAuthor(List<Book> books){
        Author author = new Author();
        Set<Book> books_author = new HashSet<>(books);
        author.setName(this.name);
        author.setAge(this.age);
        author.setBooks(books_author);
        return author;
    }
}
