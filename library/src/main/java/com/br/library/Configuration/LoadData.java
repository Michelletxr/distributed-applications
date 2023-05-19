package com.br.library.Configuration;
import com.br.library.Model.Author;
import com.br.library.Model.Book;
import com.br.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

//carregar dados no banco
@Configuration
public class LoadData {
    @Autowired
    BookRepository bookRepository;
    @Bean
    public void loadBooks(){
        for (int i = 0; i < 100; i++) {
            bookRepository.save(new Book(UUID.randomUUID(),
                    "livro-teste",
                    "suspense",
                    100,
                    5, UUID.randomUUID(),
                    new Author()));
        }
    }
}
