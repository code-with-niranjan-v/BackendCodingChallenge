package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.BookMapper;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public BookDTO addBook(BookDTO bookDTO){
        Book saved = bookRepo.save(BookMapper.toBook(bookDTO));
        return BookMapper.toBookDTO(saved);
    }

    public List<BookDTO> getAllBooks(){
        List<Book> books = bookRepo.findAll();
        return BookMapper.toBookDTOList(books);
    }

    public BookDTO findBookByISBN(String isbn) throws ResourceNotFoundException {
        Book book = bookRepo.findByIsbn(isbn);
        if (book==null) throw new ResourceNotFoundException("Book not found");
        return BookMapper.toBookDTO(book);
    }

    public void deleteBookByISBN(String isbn) throws ResourceNotFoundException {
        if(!bookRepo.existsByIsbn(isbn)){
            throw new ResourceNotFoundException("Book Not Found");
        }
        bookRepo.deleteByIsbn(isbn);
    }

    public BookDTO updateBook(int id,BookDTO bookDTO) throws ResourceNotFoundException {
        if(!bookRepo.existsById(id)){
            throw new ResourceNotFoundException("Book not found with ID: "+id);
        }
        Book book = bookRepo.findById(id).orElse(null);
        Book updatedBook = BookMapper.toBook(bookDTO);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        book.setYear(updatedBook.getYear());
        Book saved = bookRepo.save(book);
        return BookMapper.toBookDTO(saved);
    }
}
