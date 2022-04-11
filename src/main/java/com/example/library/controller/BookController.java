package com.example.library.controller;

import com.example.library.model.BookDTO;
import com.example.library.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    public static final String LOCATION = "Location";
    public static final String BOOK = "/book/";
    final
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Collection<BookDTO>> getBooks(@RequestParam int skip, @RequestParam int page) {
        Collection<BookDTO> books = bookService.getBooks( skip, page);
            return ResponseEntity
                    .ok()
                    .header(LOCATION, "/book")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(books);
    }

    @GetMapping(path = "/{bookId}")
    public ResponseEntity<BookDTO> getBook(@RequestParam Long bookId) {
        Optional<BookDTO> optionalBook = bookService.getBook(bookId);
        if (optionalBook.isPresent()) {
                return ResponseEntity
                        .ok()
                        .header(LOCATION, BOOK+bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .eTag(Long.toString(optionalBook.get().getVersion()))
                        .body(optionalBook.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
