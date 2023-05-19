package com.example.VirtualLibraryProjectLoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RepositoryBook extends JpaRepository<Book, UUID> {
    Optional<Book> findBookByName(String name);

}

