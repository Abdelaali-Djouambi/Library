package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.model.BookDTO;
import com.example.library.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControlerTest {

    @InjectMocks
    BookController bookController;

    @Mock
    BookService bookService;

    @Test
    @DisplayName("Get books list - success")
    void getBooksListSuccess(){
        List<BookDTO> bookDTOs= Mockito.mock(ArrayList.class);
        when(bookDTOs.size()).thenReturn(10);
        when(bookService.getBooks(nullable(Long.class),any(Integer.class),any(Integer.class)));
        ResponseEntity<Collection<BookDTO>> responseEntity = bookController.getBooks(null,10,0);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/book");

        assertThat(responseEntity.getBody().size()).isEqualTo(10);
    }
}
