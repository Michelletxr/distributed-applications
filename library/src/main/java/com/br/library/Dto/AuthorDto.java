package com.br.library.Dto;

import com.br.library.Model.Author;
import com.br.library.Model.Book;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthorDto {

    private UUID id;

    private String name;

    private int age;

    private UUID book_id;

    public AuthorDto(Author author){
        this.name = author.getName();
        this.age = author.getAge();
        this.book_id = author.getBook().getId();
        this.id = author.getId();
    }

    public Author buildAuthorDtoToAuthor(Book book){
        Author author = new Author();
        author.setName(this.name);
        author.setAge(this.age);
        author.setBook(book);
        return author;
    }
}
