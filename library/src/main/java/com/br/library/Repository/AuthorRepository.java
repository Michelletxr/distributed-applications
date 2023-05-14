package com.br.library.Repository;

import com.br.library.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    //Optional<Author> finAuthorByBookName(String name);
    //Optional<Author> finAuthorByBookId(UUID id);
    Optional<Author> findAuthorByName(String name);
}
