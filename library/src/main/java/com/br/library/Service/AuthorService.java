package com.br.library.Service;

import com.br.library.Dto.AuthorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AuthorService {

    UUID save(AuthorDto AuthorDto) throws Exception;
    Page<AuthorDto> findAll(Pageable pagination);

    AuthorDto findAuthorByBookName(String bookName);

    AuthorDto findAuthorByBookId(UUID id);

    boolean delete(UUID id);

    boolean update(UUID id);

}
