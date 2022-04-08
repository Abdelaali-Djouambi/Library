package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.model.BookDTO;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Collection<BookDTO>> getBooks(@RequestParam int skip, @RequestParam int page) {
        Collection<BookDTO> books = bookService.getBooks( skip, page);
            return ResponseEntity
                    .ok()
                    .header("Location", "/book")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(books);
    }

    @GetMapping
    public ResponseEntity<BookDTO> getBook(@RequestParam Long bookId) {
        Optional<BookDTO> optionalBook = bookService.getBook(bookId);
        if (optionalBook.isPresent()) {
                return ResponseEntity
                        .ok()
                        .header("Location", "/book/"+bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .eTag(Long.toString(optionalBook.get().getVersion()))
                        .location(URI.create("/book/" + optionalBook.get().getId()))
                        .body(optionalBook.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
