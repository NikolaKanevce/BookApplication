package com.application.internship.books.demo.service;

import com.application.internship.books.demo.model.Book;
import com.application.internship.books.demo.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findByISBN(String ISBN);
    List<Book> findAll();
    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(String ISBN, BookDto bookDto);
    void delete(String ISBN);
    List<Book> getBooksSortedByYear();
    List<Book> getBooksByFirstLetterOfTheAuthorSurname(String letter);
    List<Book> getOldestAndNewestBook();
}
