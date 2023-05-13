package com.br.library.Service;

import com.br.library.Dto.BookDto;
import com.br.library.Model.Book;
import com.br.library.Repository.AuthorRepository;
import com.br.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public UUID save(BookDto bookDto){
        Book book = bookDto.buildBookDtoToBook();
        return bookRepository.save(book).getId();
    }


    @Override
    public boolean update(UUID id){

        Optional<Book> book = bookRepository.findById(id);
        boolean isUpdate = false;
        if(book.isPresent()){
            bookRepository.save(book.get());
            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public Page<BookDto> findAll(Pageable pagination) {

        Page<Book> books = bookRepository.findAll(pagination);
        return  books.map(BookDto:: new);
    }

    @Override
    public BookDto findById(UUID id) {

        BookDto bookDto = null;
        Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent()){
            bookDto = new BookDto(book.get());
        }

        return bookDto;
    }

    @Override
    public boolean delete(UUID id) {

        boolean isDelet;
        Optional<Book> book = bookRepository.findById(id);

        if(!book.isPresent()){
            isDelet = false;
        }else{
            bookRepository.delete(book.get());
            isDelet = true;
        }
        return isDelet;
    }
}
