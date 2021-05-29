package com.application.internship.books.demo.bootstrap;

import com.application.internship.books.demo.model.Author;
import com.application.internship.books.demo.model.Book;
import com.application.internship.books.demo.model.EBook;
import com.application.internship.books.demo.model.PrintBook;
import com.application.internship.books.demo.model.enumeration.Format;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    public static Map<Author, List<Book>> authorBooksMap = new HashMap<>();

    @PostConstruct
    public void init(){
        authors.add(new Author("Victor", "Hugo", 1802));
        authors.add(new Author("Honore", "De Balzac", 1799));
        authors.add(new Author("Jules", "Verne", 1828));
        authors.add(new Author("Ivo", "Andric", 1892));
        for (Author author: authors) {
            authorBooksMap.put(author, new ArrayList<>());
        }

        books.add(new EBook("Book1", "1", 1850, authors.get(0), Format.EPUB, 165));
        books.add(new PrintBook("Book2","2", 1819, authors.get(1), 150, 0.5));
        books.add(new EBook("Book3","3", 1870, authors.get(2), Format.HTML5, 600));
        books.add(new EBook("Book4","4", 1921, authors.get(3), Format.PDF, 452));
        books.add(new PrintBook("Book5","5", 1829, authors.get(1), 222, 1.5));
        books.add(new PrintBook("Book6","6", 1818, authors.get(1),350, 1));
        books.add(new PrintBook("Book7","7", 1829, authors.get(1), 222, 1.5));
        books.add(new PrintBook("Book8","8", 1818, authors.get(1),350, 1));

        for (Book book: books){
            authorBooksMap.get(book.getAuthor()).add(book);
        }

    }
}
