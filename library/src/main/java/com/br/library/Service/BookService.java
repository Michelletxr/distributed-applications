package com.br.library.Service;

import com.br.library.Dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookService {

    UUID save(BookDto bookDto);
    Page<BookDto> findAll(Pageable pagination);

    BookDto findById(UUID id);

    boolean delete(UUID id);

    boolean update(UUID id);
}
