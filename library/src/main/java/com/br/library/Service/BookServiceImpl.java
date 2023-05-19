package com.br.library.Service;
import com.br.library.Dto.BookDto;
import com.br.library.Model.Author;
import com.br.library.Model.Book;
import com.br.library.Repository.AuthorRepository;
import com.br.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorServiceImpl authorService;
    @Autowired
    BookRepository bookRepository;

    @Override
    public UUID save(BookDto bookDto){
        Author author_result = null;
        Book book = null;
        try{
            Optional<Author> author = authorRepository.findAuthorByName(bookDto.getAuthor());
            author_result = author.get();
        }catch (Exception e){
            Author author = new Author();
            author.setName(bookDto.getAuthor());
            author.setBooks(new HashSet<Book>());
            author_result = authorRepository.save(author);
        }finally {
            book = bookDto.buildBookDtoToBook(author_result);
            author_result.getBooks().add(book);
            authorRepository.save(author_result);
        }
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
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> books_results = new ArrayList<>();
        books.forEach(book -> books_results.add(new BookDto(book)));
        return  books_results;
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

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }
}
