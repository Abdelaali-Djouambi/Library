package com.example.library.controller;

import com.example.library.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam Long bookId, @RequestParam int skip, @RequestParam int page){
        return null;
    }
}
