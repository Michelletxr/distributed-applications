package com.br.library.Controller;

import com.br.library.Dto.AuthorDto;
import com.br.library.Dto.BookDto;
import com.br.library.Model.Book;
import com.br.library.Service.AuthorService;
import com.br.library.Service.AuthorServiceImpl;
import com.br.library.Service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/lib")
public class LibraryController {

    @Autowired
    BookServiceImpl serviceBooks;
    @Autowired
    AuthorServiceImpl serviceAuthor;

    @Value("${server.port}")
    private int port;

    public LibraryController(AuthorServiceImpl serviceAuthor, BookServiceImpl serviceBooks){
        this.serviceAuthor = serviceAuthor;
        this.serviceBooks = serviceBooks;
    }

    public ResponseEntity<?> findAllBooks(@PageableDefault(size = 5, sort={"id"}, direction = Sort.Direction.DESC) Pageable pagination){
        ResponseEntity<?> response;
        Page<BookDto> bookDto = serviceBooks.findAll(pagination);
        response = new ResponseEntity<>(bookDto, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findByBookId(@PathVariable UUID id){

        BookDto bookDto =  serviceBooks.findById(id);
        ResponseEntity<Object> response;

        if(Objects.isNull(bookDto)){
            response = new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity<>(bookDto, HttpStatus.OK);
        }
        return response;
    }

    @GetMapping(value = "author")
    public ResponseEntity<Object> findAuthor(@RequestParam(required = false) UUID id_book, @RequestParam(required = false)String book_name, Pageable pagination){

        AuthorDto author = null;
        ResponseEntity<Object> response;

        if(Objects.nonNull(id_book)){
            author = serviceAuthor.findAuthorByBookId(id_book);
        } else if (Objects.nonNull(book_name)) {
            author = serviceAuthor.findAuthorByBookName(book_name);
        }else{
            Page<AuthorDto> authorsDto = serviceAuthor.findAll(pagination);
        }

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping
    @CacheEvict(value = {"books"}, allEntries = true)
    public ResponseEntity<UUID> saveBook(@RequestBody @Validated BookDto bookDto){
        UUID bookId = serviceBooks.save(bookDto);
        ResponseEntity<UUID> response = new ResponseEntity<UUID>(bookId, HttpStatus.OK);
        return response;
    }

    @PostMapping(value = "author-register")
    public ResponseEntity<UUID> saveAuthor(@RequestBody @Validated AuthorDto authorDto) throws Exception {
        UUID authorId = serviceAuthor.save(authorDto);
        ResponseEntity<UUID> response = new ResponseEntity<UUID>(authorId, HttpStatus.OK);
        return response;
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateBook(@PathVariable UUID id,  @RequestBody BookDto bookRequest){
        ResponseEntity<?> response;

        try{
            boolean isUpdate = serviceBooks.update(id);

            if(isUpdate){
                response = new ResponseEntity<>(id, HttpStatus.OK);
            }else{
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @PutMapping(value = "author/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable UUID id,  @RequestBody AuthorDto authorRequest){
        ResponseEntity<?> response;

        try{
            boolean isUpdate = serviceAuthor.update(id);

            if(isUpdate){
                response = new ResponseEntity<>(id, HttpStatus.OK);
            }else{
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @DeleteMapping(value = "{id}")
    @CacheEvict(value = {"books"}, allEntries = true)
    public ResponseEntity<?> deleteBook(@PathVariable UUID id){

        ResponseEntity<?> response;
        boolean isDelet = serviceBooks.delete(id);

        if(!isDelet){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity<>(HttpStatus.OK);
        }

        return response;
    }

    @DeleteMapping(value = "author/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable UUID id){

        ResponseEntity<?> response;
        boolean isDelet = serviceAuthor.delete(id);

        if(!isDelet){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity<>(HttpStatus.OK);
        }

        return response;
    }

}
