package com.example.VirtualLibraryProjectLoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ServiceBook {
    @Autowired
    RepositoryBook bookRepository;
    List<Book> findAll(){
        return bookRepository.findAll();
    };


}
