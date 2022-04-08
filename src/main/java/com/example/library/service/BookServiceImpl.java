package com.example.library.service;

import com.example.library.model.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Override
    public List<BookDTO> getBooks(int skip, int page) {
        return null;
    }

    @Override
    public Optional<BookDTO> getBook(Long bookId) {
        return Optional.empty();
    }
}
