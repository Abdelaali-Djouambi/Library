package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.exceptions.ResourceNotFoundException;
import com.example.library.fixture.BookFixture;
import com.example.library.model.BookDTO;
import com.example.library.repository.BookRepository;
import com.example.library.util.ModelMapperUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import javax.print.attribute.standard.PageRanges;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    ModelMapperUtil modelMapperUtil;

    @Test
    @DisplayName("Find book by id test - Success")
    void get_book_byId_success(){
        Book book = new Book("Lord of the ring",  "JRR Tolkien",20l, 100l);
        BookDTO bookDTO = new BookDTO("Lord of the ring", "JRR Tolkien",20l,  100l);
        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(book));
        when(modelMapperUtil.mapBookToBookDTO(any(Book.class))).thenReturn(bookDTO);
        Optional<BookDTO> optionalBookDTO = bookService.getBook(1l);

        assertEquals(optionalBookDTO.get().getAuthor(),book.getAuthor());
        assertEquals(optionalBookDTO.get().getTitle(),book.getTitle());
    }

    @Test
    @DisplayName("Find book by id test - Not Found")
    void get_book_byId_error_not_found(){
        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->bookService.getBook(1l));
    }

    @Test
    @DisplayName("Find book list - success")
    void get_book_list_success(){
        List<Book> books= BookFixture.getListBooks();
        when(bookRepository.findALlByAuthor(any(String.class),any(Pageable.class))).thenReturn(books);
        List<BookDTO> returnedBooks = bookService.getBooks(0,10);
        assertEquals(10,returnedBooks.size());
    }
}
