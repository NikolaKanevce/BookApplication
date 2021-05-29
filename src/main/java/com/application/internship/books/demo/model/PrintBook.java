package com.application.internship.books.demo.model;

import com.application.internship.books.demo.model.enumeration.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PrintBook extends Book {
    @JsonProperty
    private int numberOfPages;
    @JsonProperty
    private double weight;


    public PrintBook(String name, String ISBN, int publishYear, Author author, int numberOfPages, double weight) {
        super(name, ISBN, publishYear, author);
        this.numberOfPages = numberOfPages;
        this.weight = weight;
    }

    @Override
    public Type getType() {
        return Type.PRINT_COPY;
    }
}
