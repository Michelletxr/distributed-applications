package com.br.library.Dto;

import com.br.library.Model.Author;
import com.br.library.Model.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BookDto {


    private String name;

    private String genre;

    private int numberPages;

    private float rating;

    private String author;

    public BookDto(Book book) {
        this.name = book.getName();
        this.genre = book.getGenre();
        this.numberPages = book.getNumberPages();
        this.rating = book.getRating();
        this.author = book.getAuthor().getName();
    }

    public Book buildBookDtoToBook(Author author){
        Book book = new Book();
        book.setName(this.name);
        book.setGenre(this.genre);
        book.setNumberPages(this.numberPages);
        book.setRating(this.rating);
        book.setAuthor(author);
        if(Objects.nonNull(author)){book.setAuthor(author);}
        return book;
    }

}
