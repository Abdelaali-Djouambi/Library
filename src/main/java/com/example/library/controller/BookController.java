package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.model.BookDTO;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    public static final String LOCATION = "Location";
    public static final String BOOK = "/game/";
    final
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Collection<BookDTO>> getBooks(@RequestParam Long bookId, @RequestParam int skip, @RequestParam int page) {
        Collection<BookDTO> books = bookService.getBooks(bookId, skip, page);
        return ResponseEntity
                .ok()
                .header("Location", "/book")
                .contentType(MediaType.APPLICATION_JSON)
                .body(books);
    }
}
