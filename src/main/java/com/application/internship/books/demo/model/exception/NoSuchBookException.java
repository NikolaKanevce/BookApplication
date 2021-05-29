package com.application.internship.books.demo.model.exception;

public class NoSuchBookException extends RuntimeException {
    public NoSuchBookException(String ISBN){
        super(String.format("Book with ISBN: %s is not found", ISBN));
    }
}
