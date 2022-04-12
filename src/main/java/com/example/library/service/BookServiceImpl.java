package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.exceptions.ErrorCode;
import com.example.library.exceptions.ResourceNotFoundException;
import com.example.library.model.BookDTO;
import com.example.library.repository.BookRepository;
import com.example.library.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    private final ModelMapperUtil modelMapperUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapperUtil modelMapperUtil) {
        this.bookRepository = bookRepository;
        this.modelMapperUtil = modelMapperUtil;
    }

    @Override
    public List<BookDTO> getBooks(int skip, int page) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Optional<BookDTO> getBook(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = optionalBook.orElseThrow(()-> new ResourceNotFoundException(ErrorCode.BOOK_NOT_FOUND.getDefaultMessage()));
        return Optional.of(modelMapperUtil.mapBookToBookDTO(book));
    }
}
