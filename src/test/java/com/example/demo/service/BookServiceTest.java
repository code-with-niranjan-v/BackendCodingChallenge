package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.BookMapper;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepo;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;




    @Test
    public void shouldAddBookSuccessfully(){
        Book book = new Book(null,"Sample","abc","1234567890123",2020);
        Book saved = BookMapper.toBook(bookService.addBook(BookMapper.toBookDTO(book)));
        assertNotNull(saved);
        assertEquals(book.getIsbn(),saved.getIsbn());


    }


    @Test
    void shouldUpdateBookSuccessFully() throws ResourceNotFoundException {
        Book old = new Book(null,"Sample","abc","1234567890123",2020);
        BookDTO saved = bookService.addBook(BookMapper.toBookDTO(old));
        BookDTO bookDTO = new BookDTO(null,"Sample Updated","abcUpdated","1234567890123",2020);
        BookDTO updateBook = bookService.updateBook(saved.getId(),bookDTO);
        assertNotNull(updateBook);
        assertEquals(bookDTO.getAuthor(),updateBook.getAuthor());
        assertEquals(bookDTO.getTitle(),updateBook.getTitle());
    }

    @Test
    void shouldDeleteBookSuccessFully() throws ResourceNotFoundException {
        Book old = new Book(null,"Sample","abc","1234567890123",2020);
        BookDTO saved = bookService.addBook(BookMapper.toBookDTO(old));
        bookService.deleteBookByISBN("1234567890123");
        assertThrows(ResourceNotFoundException.class,()->{bookService.findBookByISBN("1234567890123");});
    }

    @Test
    void shouldGetBookByISBNUnSuccessfully() throws ResourceNotFoundException{
        assertThrows(ResourceNotFoundException.class,()->{bookService.findBookByISBN("1234");});
    }

    @Test
    void shouldGetBookByISBNSuccessfully() throws ResourceNotFoundException {
        Book old = new Book(null,"Sample","abc","1234567890123",2020);
        BookDTO saved = bookService.addBook(BookMapper.toBookDTO(old));
        assertNotNull(bookService.findBookByISBN("1234567890123"));
        assertEquals("1234567890123", saved.getIsbn());
    }

}
