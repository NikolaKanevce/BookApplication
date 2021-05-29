package com.application.internship.books.demo.model;

import com.application.internship.books.demo.model.enumeration.Format;
import com.application.internship.books.demo.model.enumeration.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EBook extends Book {
    @JsonProperty
    private Format format;
    @JsonProperty
    private double sizeInMB;

    public EBook(String name, String ISBN, int publishYear, Author author, Format format, double sizeInMB) {
        super(name, ISBN, publishYear,  author);
        this.format = format;
        this.sizeInMB = sizeInMB;
    }

    @Override
    public Type getType() {
        return Type.E_BOOK;
    }
}
