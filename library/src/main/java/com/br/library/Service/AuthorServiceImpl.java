package com.br.library.Service;

import com.br.library.Dto.AuthorDto;
import com.br.library.Model.Author;
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
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public UUID save(AuthorDto authorDto) throws Exception {
        Optional<Book> book = bookRepository.findById(authorDto.getBook_id());
        UUID author_id = null;
        if(book.isPresent()){
            Author author = authorRepository.save(authorDto.buildAuthorDtoToAuthor(book.get()));
            author_id = author.getId();
        }else{
            throw new Exception("O livro informado não existe");
        }
        return author_id;
    }

    @Override
    public Page<AuthorDto> findAll(Pageable pagination) {
        Page<Author> author = authorRepository.findAll(pagination);
        return  author.map(AuthorDto:: new);
    }

    @Override
    public AuthorDto findAuthorByBookId(UUID id) {
        Optional<Author> author = authorRepository.finAuthorByBookId(id);
        if(author.isPresent()){
            return new AuthorDto(author.get());
        }
        return null;
    }

    @Override
    public AuthorDto findAuthorByBookName(String bookName) {
        Optional<Author> author = authorRepository.finAuthorByBookName(bookName);
        if(author.isPresent()){
            return new AuthorDto(author.get());
        }
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
