package com.example.library.service;

import com.example.library.model.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getBooks(Long bookId, int skip, int page);
}
