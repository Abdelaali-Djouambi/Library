package com.example.library.controller;

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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    BookController bookController;

    @Mock
    BookService bookService;

    @Test
    @DisplayName("Get books list - success")
    void get_book_list_success(){
        List<BookDTO> bookDTOs= Mockito.mock(ArrayList.class);
        when(bookDTOs.size()).thenReturn(10);
        when(bookService.getBooks(any(Integer.class),any(Integer.class))).thenReturn(bookDTOs);
        ResponseEntity<Collection<BookDTO>> responseEntity = bookController.getBooks(10,0);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/book");

        assertThat(responseEntity.getBody()).hasSize(10);
    }

    @Test
    @DisplayName("Get books by id - success")
    void find_book_byId_success(){
        BookDTO bookDTO= new BookDTO("Lord of the ring", "J.R.R Tolkien",30l,200l);
        bookDTO.setVersion(1l);
        bookDTO.setId(1l);
        List<BookDTO> bookDTOs= Mockito.spy(ArrayList.class);
        bookDTOs.add(bookDTO);
        when(bookService.getBook(any(Long.class))).thenReturn(Optional.of(bookDTO));
        ResponseEntity<BookDTO> responseEntity = bookController.getBook(1l);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/book/1");

        assertThat(responseEntity.getBody().getTitle()).isEqualTo("Lord of the ring");
    }

    @Test
    @DisplayName("Get books by id - Not found")
    void find_book_byId_not_found(){
        when(bookService.getBook(nullable(Long.class))).thenReturn(Optional.empty());
        ResponseEntity<BookDTO> responseEntity = bookController.getBook(1l);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }
}
