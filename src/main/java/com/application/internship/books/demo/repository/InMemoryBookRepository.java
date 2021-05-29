package com.application.internship.books.demo.repository;

import com.application.internship.books.demo.bootstrap.DataHolder;
import com.application.internship.books.demo.model.Book;
import com.application.internship.books.demo.model.EBook;
import com.application.internship.books.demo.model.PrintBook;
import com.application.internship.books.demo.model.dto.BookDto;
import com.application.internship.books.demo.model.enumeration.Type;
import com.application.internship.books.demo.model.exception.NoSuchBookException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookRepository {
    public List<Book> findAll(){
        return DataHolder.books;
    }

    public Optional<Book> findByISBN(String ISBN){
        return DataHolder.books.stream().filter(s->s.getISBN().equals(ISBN)).findFirst();
    }

    public Optional<Book> save(BookDto bookDto){
        Book book;
        DataHolder.books.removeIf(s->s.getISBN().equals(bookDto.getISBN()));
        if(bookDto.getType().equals(Type.E_BOOK)){
            book = new EBook(bookDto.getName(), bookDto.getISBN(), bookDto.getPublishYear(), bookDto.getAuthor(), bookDto.getFormat(), bookDto.getSize());
            DataHolder.books.add(book);
            if(!DataHolder.authorBooksMap.containsKey(book.getAuthor())){
                DataHolder.authorBooksMap.put(book.getAuthor(), new ArrayList<>());
            }
            DataHolder.authorBooksMap.get(book.getAuthor()).add(book);
        }
        else {
            book = new PrintBook(bookDto.getName(), bookDto.getISBN(), bookDto.getPublishYear(), bookDto.getAuthor(), bookDto.getNumberOfPages(), bookDto.getWeight());
            DataHolder.books.add(book);
            if(!DataHolder.authorBooksMap.containsKey(book.getAuthor())){
                DataHolder.authorBooksMap.put(book.getAuthor(), new ArrayList<>());
            }
            DataHolder.authorBooksMap.get(book.getAuthor()).add(book);
        }
        return Optional.of(book);
    }

    public Optional<Book> edit(String ISBN, BookDto bookDto){
        Book changedBook;
        Book book = this.findByISBN(ISBN).orElseThrow(() -> new NoSuchBookException(ISBN));
        DataHolder.books.remove(book);
        if(bookDto.getType().equals(Type.E_BOOK)){
            changedBook = new EBook(bookDto.getName(), ISBN, bookDto.getPublishYear(), bookDto.getAuthor(), bookDto.getFormat(), bookDto.getSize());
            DataHolder.books.add(changedBook);
        }
        else {
            changedBook = new PrintBook(bookDto.getName(), ISBN, bookDto.getPublishYear(), bookDto.getAuthor(), bookDto.getNumberOfPages(), bookDto.getWeight());
            DataHolder.books.add(changedBook);
        }
        DataHolder.authorBooksMap.get(book.getAuthor()).removeIf(s->s.getISBN().equals(ISBN));
        DataHolder.authorBooksMap.get(book.getAuthor()).add(changedBook);
        return Optional.of(changedBook);
    }

    public void deleteBook(String ISBN){
        Book book = this.findByISBN(ISBN).orElseThrow(() -> new NoSuchBookException(ISBN));
        DataHolder.books.remove(book);
        DataHolder.authorBooksMap.get(book.getAuthor()).removeIf(s->s.getISBN().equals(book.getISBN()));
    }
}
