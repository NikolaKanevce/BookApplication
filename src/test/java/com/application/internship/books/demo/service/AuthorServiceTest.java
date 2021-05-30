package com.application.internship.books.demo.service;

import com.application.internship.books.demo.model.Author;
import com.application.internship.books.demo.model.Book;
import com.application.internship.books.demo.model.enumeration.Type;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    @Test
    public void testForAuthorsWithMoreThanThreeBooks(){
        List<Author> authors = this.authorService.getAuthorsWithMoreThanThreeBooks();
        for (Author author : authors){
            Assert.assertTrue(authorService.getAuthorsWithBooks().get(author).size()>3);
        }
    }

    @Test
    public void testForAuthorsDecade(){
        List<Author> authors = this.authorService.authorsWithSameDecadeAsBooks();
        Set<Integer> bookDecades= this.bookService.findAll().stream().filter(s->s.getType().equals(Type.PRINT_COPY)).map(Book::getDecade).collect(Collectors.toSet());
        for (Author author: authors){
            Assert.assertTrue(bookDecades.contains(author.getDecade()));
        }
    }
}
