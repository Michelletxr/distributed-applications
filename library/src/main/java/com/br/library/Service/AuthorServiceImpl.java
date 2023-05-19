package com.br.library.Service;
import com.br.library.Dto.AuthorDto;
import com.br.library.Model.Author;
import com.br.library.Model.Book;
import com.br.library.Repository.AuthorRepository;
import com.br.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public UUID save(AuthorDto authorDto) throws Exception {
        List<Book> books = new ArrayList<>();
        UUID author_id = null;

        for (UUID id: authorDto.getBooks()) {
            Optional<Book> book = bookRepository.findById(id);
            if(book.isPresent()) books.add(book.get());
        }

        if(!books.isEmpty()){
            Author author = authorRepository.save(authorDto.buildAuthorDtoToAuthor(books));
            author_id = author.getId();
        }else{
            throw new Exception("O livro informado não existe");
        }
        return author_id;
    }

    @Override
    public List<AuthorDto> findAll() {
        List<Author> author = authorRepository.findAll();
        List<AuthorDto> authorDtos = new ArrayList<>();
        author.forEach(a -> authorDtos.add(new AuthorDto(a)));
        return  authorDtos;
    }

    @Override
    public AuthorDto findAuthorByBookId(UUID id) {
       /* Optional<Author> author = authorRepository.finAuthorByBookId(id);
        if(author.isPresent()){
            return new AuthorDto(author.get());
        }*/
        return null;
    }
    public AuthorDto findAuthorById(UUID id) {
       Optional<Author> author = authorRepository.findById(id);
       AuthorDto response = null;
        if(author.isPresent()){
            response = new AuthorDto(author.get());
        }
        return response;
    }

    @Override
    public AuthorDto findAuthorByBookName(String bookName) {
        /*Optional<Author> author = authorRepository.finAuthorByBookName(bookName);
        if(author.isPresent()){
            return new AuthorDto(author.get());
        }*/
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        boolean isDelete = false;
        Optional<Author> author = authorRepository.findById(id);
        if(author.isPresent()){
            authorRepository.delete(author.get());
            isDelete = true;
        }
        return isDelete;
    }

    @Override
    public boolean update(UUID id) {

        Optional<Author> author = authorRepository.findById(id);
        boolean isUpdate = false;
        if(author.isPresent()){
            authorRepository.save(author.get());
            isUpdate = true;
        }
        return isUpdate;
    }
}
