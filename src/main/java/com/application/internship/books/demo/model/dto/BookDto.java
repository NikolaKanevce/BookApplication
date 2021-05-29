package com.application.internship.books.demo.model.dto;

import com.application.internship.books.demo.model.Author;
import com.application.internship.books.demo.model.enumeration.Format;
import com.application.internship.books.demo.model.enumeration.Type;
import lombok.Data;

@Data
public class BookDto {
    private String name;
    private String ISBN;
    private int publishYear;
    private Type type;
    private Author author;
    private Format format;
    private double size;
    private int numberOfPages;
    private double weight;
}
