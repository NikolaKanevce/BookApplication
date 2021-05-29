package com.application.internship.books.demo.service.impl;

import com.application.internship.books.demo.model.Book;
import com.application.internship.books.demo.model.dto.BookDto;
import com.application.internship.books.demo.repository.InMemoryBookRepository;
import com.application.internship.books.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final InMemoryBookRepository inMemoryBookRepository;

    @Override
    public Optional<Book> findByISBN(String ISBN) {
        return this.inMemoryBookRepository.findByISBN(ISBN);
    }

    @Override
    public List<Book> findAll() {
        return inMemoryBookRepository.findAll();
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        return inMemoryBookRepository.save(bookDto);
    }

    @Override
    public Optional<Book> edit(String ISBN, BookDto bookDto) {
        return inMemoryBookRepository.edit(ISBN, bookDto);
    }

    @Override
    public void delete(String ISBN) {
        inMemoryBookRepository.deleteBook(ISBN);
    }

    @Override
    public List<Book> getBooksSortedByYear() {
        return this.inMemoryBookRepository.findAll().stream().sorted(Comparator.comparing(Book::getPublishYear)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByFirstLetterOfTheAuthorSurname(String letter) {
        return this.inMemoryBookRepository.findAll().stream().filter(s->s.getAuthor().getSurname().startsWith(letter)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getOldestAndNewestBook() {
        List<Book> bookList = this.inMemoryBookRepository.findAll().stream().sorted(Comparator.comparing(Book::getPublishYear)).collect(Collectors.toList());
        List<Book> oldestAndNewest = new ArrayList<>();
        oldestAndNewest.add(bookList.get(0));
        oldestAndNewest.add(bookList.get(bookList.size() - 1));
        return oldestAndNewest;
    }
}
