package com.application.internship.books.demo.model;

import lombok.Data;

@Data
public class Author {
    private String name;
    private String surname;
    private int birthYear;
    private int decade;

    public Author(String name, String surname, int birthYear) {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
        this.decade = this.birthYear/10;
    }
}
