package com.example.demo.mapper;

import com.example.demo.dto.BookDTO;
import com.example.demo.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static BookDTO toBookDTO(Book book){
        BookDTO bookDTO = new BookDTO(book.getId(), book.getTitle(),  book.getAuthor(), book.getIsbn(),book.getYear());
        return bookDTO;
    }

    public static Book toBook(BookDTO bookDTO){
        Book book = new Book(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getIsbn(), bookDTO.getYear());
        return book;
    }


    public static List<BookDTO> toBookDTOList(List<Book> books){
        if(books!=null){
            return books.stream().map(BookMapper::toBookDTO).toList();
        }

        return new ArrayList<BookDTO>();

    }

    public static List<Book> toBookList(List<BookDTO> books){
        if(books!=null){
            return books.stream().map(BookMapper::toBook).toList();
        }

        return new ArrayList<Book>();

    }


}
