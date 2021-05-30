package com.application.internship.books.demo.service.impl;

import com.application.internship.books.demo.model.Author;
import com.application.internship.books.demo.model.Book;
import com.application.internship.books.demo.model.enumeration.Type;
import com.application.internship.books.demo.repository.InMemoryAuthorRepository;
import com.application.internship.books.demo.repository.InMemoryBookRepository;
import com.application.internship.books.demo.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final InMemoryAuthorRepository inMemoryAuthorRepository;
    private final InMemoryBookRepository inMemoryBookRepository;

    @Override
    public Map<Author, List<Book>> getAuthorsWithBooks() {
        return this.inMemoryAuthorRepository.getAuthorsWithBooks();
    }

    @Override
    public List<Author> authorsWithSameDecadeAsBooks() {
        Set<Integer> decadesOfBooks = new HashSet<>();
        for(Book book: this.inMemoryBookRepository.findAll()){
            if(book.getType().equals(Type.PRINT_COPY)){
                decadesOfBooks.add(book.getDecade());
            }
        }
        return this.inMemoryAuthorRepository.findAll().stream().filter(s->decadesOfBooks.contains(s.getDecade())).collect(Collectors.toList());
    }

    @Override
    public List<Author> getAuthorsWithMoreThanThreeBooks() {
        List<Author> authors = new ArrayList<>();
        Map<Author, List<Book>> authorsWithBooks = this.inMemoryAuthorRepository.getAuthorsWithBooks();
        for(Author author : authorsWithBooks.keySet()){
            if(authorsWithBooks.get(author).size() > 3){
                authors.add(author);
            }
        }
        return authors;
    }

    /* If the attribute decade is not allowed in Book and Author then below is the solution I would give for this method.
    * But for more optimal algorithm I added attribute decade in both classes.
    * */
    /*@Override
    public List<Author> authorsWithSameDecadeAsBooks() {
        Set<Author> authors = new HashSet<>();
        this.inMemoryBookRepository.findAll().forEach(s->authors.add(s.getAuthor()));

        List<Author> authorsInSameDecade = new ArrayList<>();
        for(Author author: authors){
            if(this.inMemoryBookRepository.findAll().stream().filter(book-> book.getType().equals(Type.PRINT_COPY) &&
                    book.getPublishYear() >= (author.getBirthYear()/10)*10 &&
                    book.getPublishYear() < ((author.getBirthYear()/10) * 10) + 10).count() > 0){
                authorsInSameDecade.add(author);
            }
        }
        return authorsInSameDecade;
    }*/
}
