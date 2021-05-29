package com.application.internship.books.demo.model;

import com.application.internship.books.demo.model.enumeration.Type;
import lombok.Data;


@Data
public abstract class Book {
    private String name;
    private String ISBN;
    private int publishYear;
    private Author author;
    private int decade;

    Book(String name, String ISBN, int publishYear, Author author) {
        this.name = name;
        this.ISBN = ISBN;
        this.publishYear = publishYear;
        this.author = author;
        this.decade = this.publishYear/10;
    }

    @Override
    public String toString() {
        return this.ISBN;
    }

    public abstract Type getType();

}
