package com.application.internship.books.demo.service;

import com.application.internship.books.demo.model.Author;
import com.application.internship.books.demo.model.Book;

import java.util.List;
import java.util.Map;

public interface AuthorService {
    Map<Author, List<Book>> getAuthorsWithBooks();
    List<Author> authorsWithSameDecadeAsBooks();
    List<Author> getAuthorsWithMoreThanThreeBooks();
}
