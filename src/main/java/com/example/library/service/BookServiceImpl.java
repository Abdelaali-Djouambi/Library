package com.example.library.service;

import com.example.library.model.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Override
    public List<BookDTO> getBooks(Long bookId, int skip, int page) {
        return null;
    }
}
