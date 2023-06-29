package com.br.library.Controller;

import com.br.library.Dto.AuthorDto;
import com.br.library.Dto.BookDto;
import com.br.library.Service.AuthorServiceImpl;
import com.br.library.Service.BookServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    private int serverPort;

    @GetMapping
    public ResponseEntity list(){
        return ResponseEntity.ok("OK from " + serverPort);
    }

    public LibraryController(AuthorServiceImpl serviceAuthor, BookServiceImpl serviceBooks){
        this.serviceAuthor = serviceAuthor;
        this.serviceBooks = serviceBooks;
    }

    @GetMapping(value = "book")
    public ResponseEntity<?> findAllBooks(){
        ResponseEntity<?> response;
        List<BookDto> bookDto = serviceBooks.findAll();
        response = new ResponseEntity<>(bookDto, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "book/{id}")
    public ResponseEntity<Object> findBook(@PathVariable UUID id){

        BookDto bookDto =  serviceBooks.findById(id);
        ResponseEntity<Object> response;

        if(Objects.isNull(bookDto)){
            response = new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity<>(bookDto, HttpStatus.OK);
        }
        return response;
    }

    @PutMapping(value = "book/{id}")
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

    @PostMapping(value = "book")
    @CacheEvict(value = {"books"}, allEntries = true)
    public ResponseEntity<UUID> saveBook(@RequestBody @Validated BookDto bookDto){
        UUID bookId = serviceBooks.save(bookDto);
        ResponseEntity<UUID> response = new ResponseEntity<UUID>(bookId, HttpStatus.OK);
        return response;
    }

    @DeleteMapping(value = "book/{id}")
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

    @DeleteMapping(value = "book")
    public void deleteAllBooks(){
        serviceBooks.deleteAll();
    }


    @PostMapping(value = "author")
    public ResponseEntity<?> saveAuthor(@RequestBody @Validated AuthorDto authorDto) throws Exception {
        ResponseEntity<?> response = null;
        try {
            UUID authorId = serviceAuthor.save(authorDto);
             response = new ResponseEntity<UUID>(authorId, HttpStatus.OK);
        }catch (Exception e){
            response = new ResponseEntity<>("erro ao tentar cadastrar autor!", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping(value = "author")
    public ResponseEntity<?> findAllAuthors(){
        ResponseEntity<?> response;
        List<AuthorDto> authorDtos = serviceAuthor.findAll();
        response = new ResponseEntity<>(authorDtos, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "author/{id}")
    public ResponseEntity<?> findAuthor(@PathVariable UUID id){

        AuthorDto author = null;
        ResponseEntity<?> response;

        if(Objects.nonNull(id)){
            author = serviceAuthor.findAuthorById(id);
            response = new ResponseEntity<>(author, HttpStatus.OK);
        }else{
            response = new ResponseEntity<>("erro", HttpStatus.BAD_REQUEST);
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

    @GetMapping("/threads")
    public String getBooks() {
        List<BookDto> books = serviceBooks.findAll();
        return "response: " + books +  " current thread: " + Thread.currentThread();
    }

}
