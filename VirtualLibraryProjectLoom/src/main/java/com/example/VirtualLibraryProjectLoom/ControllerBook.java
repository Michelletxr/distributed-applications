package com.example.VirtualLibraryProjectLoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping(value = "lib")
public class ControllerBook {
    @Autowired
    private  RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private ServiceBook serviceBook;

    String uri = "http://localhost:8000/api/lib/book";

    //retornas livros e a thred
    @GetMapping("/book/threads")
    String getBooks(){
        List<Book> books = serviceBook.findAll();
        return "response: " + books + " current thread: " + Thread.currentThread();
    }

}
