package com.br.library.Dto;

import com.br.library.Model.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BookDto {

    private UUID id;

    private String name;

    private String genre;

    private int numberPages;

    private float rating;

    public BookDto(Book book) {
        this.name = book.getName();
        this.genre = book.getGenre();
        this.numberPages = book.getNumberPages();
        this.rating = book.getRating();
        this.id = book.getId();
    }

    public Book buildBookDtoToBook(){
        Book book = new Book();
        book.setName(this.name);
        book.setGenre(this.genre);
        book.setNumberPages(this.numberPages);
        book.setRating(this.rating);
        return book;
    }

}
