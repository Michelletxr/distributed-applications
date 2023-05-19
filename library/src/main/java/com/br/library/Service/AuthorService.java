package com.br.library.Service;
import com.br.library.Dto.AuthorDto;
import java.util.List;
import java.util.UUID;

public interface AuthorService {

    UUID save(AuthorDto AuthorDto) throws Exception;
    List<AuthorDto> findAll();
    AuthorDto findAuthorByBookName(String bookName);
    AuthorDto findAuthorByBookId(UUID id);
    boolean delete(UUID id);
    boolean update(UUID id);

}
