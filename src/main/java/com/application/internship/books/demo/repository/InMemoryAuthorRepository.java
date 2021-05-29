package com.application.internship.books.demo.repository;

import com.application.internship.books.demo.bootstrap.DataHolder;
import com.application.internship.books.demo.model.Author;
import com.application.internship.books.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class InMemoryAuthorRepository {
    public List<Author> findAll(){
        return DataHolder.authors;
    }
    public Map<Author, List<Book>> getAuthorsWithBooks(){
        return DataHolder.authorBooksMap;
    }

}
