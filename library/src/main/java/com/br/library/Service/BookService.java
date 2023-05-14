package com.br.library.Service;

import com.br.library.Dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookService {

    UUID save(BookDto bookDto);
    List<BookDto> findAll();

    BookDto findById(UUID id);

    boolean delete(UUID id);

    boolean update(UUID id);
}
