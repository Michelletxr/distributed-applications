package com.br.library.Controller;


import com.br.library.Dto.BookDto;
import com.br.library.Service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.Cacheable;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/cache/lib")
public class CacheController {

    @Autowired
    BookServiceImpl serviceBooks;
    @GetMapping(value = "book")
    @Cacheable(value = "books")
    public ResponseEntity<?> findAllBooks(){
        ResponseEntity<?> response;
        List<BookDto> bookDto = serviceBooks.findAll();
        response = new ResponseEntity<>(bookDto, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "book/{id}")
    @Cacheable(value = "books")
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
}
