package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<?>> addBook(@RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(new ApiResponse<>(200,"Book was added successfully!",bookService.addBook(bookDTO)));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<?>> getAllBooks(){
        return ResponseEntity.ok(new ApiResponse<>(200,"All books were received!",bookService.getAllBooks()));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<ApiResponse<?>> getBookByISBN(@PathVariable String isbn) throws ResourceNotFoundException {
        return ResponseEntity.ok(new ApiResponse<>(200,"Books are received",bookService.findBookByISBN(isbn)));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<ApiResponse<?>> deleteBookByISBN(@PathVariable String isbn) throws ResourceNotFoundException {
        bookService.deleteBookByISBN(isbn);
        return ResponseEntity.ok(new ApiResponse<>(200,"Book was Deleted!",""));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateBook(@PathVariable int id,@RequestBody BookDTO bookDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(new ApiResponse<>(200,"Book was Updated",bookService.updateBook(id,bookDTO)));
    }


}
