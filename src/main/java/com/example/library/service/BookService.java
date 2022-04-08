package com.example.library.service;

import com.example.library.model.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDTO> getBooks(int skip, int page);
    Optional<BookDTO> getBook(Long bookId);
}
