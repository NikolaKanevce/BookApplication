package com.application.internship.books.demo.service;

import com.application.internship.books.demo.model.Author;
import com.application.internship.books.demo.model.Book;
import com.application.internship.books.demo.model.EBook;
import com.application.internship.books.demo.model.PrintBook;
import com.application.internship.books.demo.model.dto.BookDto;
import com.application.internship.books.demo.model.enumeration.Format;
import com.application.internship.books.demo.model.enumeration.Type;
import com.application.internship.books.demo.model.exception.NoSuchBookException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    public void testCreateNewEBook(){
        List<Book> list = this.bookService.findAll();
        int oldSize = list.size();
        BookDto bookDto = new BookDto();
        bookDto.setName("0");
        bookDto.setISBN("0");
        bookDto.setPublishYear(0);
        bookDto.setAuthor(new Author("name", "surname", 2000));
        bookDto.setFormat(Format.EPUB);
        bookDto.setType(Type.E_BOOK);
        bookDto.setSize(20);
        this.bookService.save(bookDto);
        Assert.assertEquals(oldSize + 1, this.bookService.findAll().size());
    }

    @Test
    public void testCreateNewPrintBook(){
        List<Book> list = this.bookService.findAll();
        int oldSize = list.size();
        BookDto bookDto = new BookDto();
        bookDto.setName("0");
        bookDto.setISBN("10");
        bookDto.setPublishYear(0);
        bookDto.setAuthor(new Author("name", "surname", 2000));
        bookDto.setNumberOfPages(100);
        bookDto.setType(Type.PRINT_COPY);
        bookDto.setWeight(0.5);
        this.bookService.save(bookDto);
        Assert.assertEquals(oldSize + 1, this.bookService.findAll().size());
    }

    @Test
    public void testEditBook(){
        Book book = this.bookService.findByISBN("2").orElseThrow(() -> new NoSuchBookException("2"));
        String oldName = book.getName();
        BookDto bookDto = new BookDto();
        bookDto.setName("newName");
        bookDto.setISBN(book.getISBN());
        bookDto.setPublishYear(book.getPublishYear());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setType(book.getType());

        if (book.getType().equals(Type.E_BOOK)){
            bookDto.setFormat(((EBook) book).getFormat());
            bookDto.setSize(((EBook) book).getSizeInMB());
        }
        if (book.getType().equals(Type.PRINT_COPY)){
            bookDto.setNumberOfPages(((PrintBook) book).getNumberOfPages());
            bookDto.setWeight(((PrintBook) book).getWeight());
        }
        this.bookService.edit("2", bookDto);
        Assert.assertNotEquals(oldName, this.bookService.findByISBN("2").orElseThrow(() -> new NoSuchBookException("2")).getName());
    }

    @Test
    public void testSortingByYear(){
        List<Book> books = this.bookService.getBooksSortedByYear();
        Book oldest = this.bookService.getOldestAndNewestBook().get(0);
        Assert.assertEquals(books.get(0), oldest);
        Book newest = this.bookService.getOldestAndNewestBook().get(1);
        Assert.assertEquals(books.get(books.size() - 1), newest);
    }

    @Test
    public void testForAuthorsSurnameLetter(){
        List<Book> books = this.bookService.getBooksByFirstLetterOfTheAuthorSurname("D");
        for(Book book: books){
            Assert.assertEquals('D', book.getAuthor().getSurname().charAt(0));
        }
    }

    @Test
    public void testDeleteBook(){
        List<Book> list = this.bookService.findAll();
        int oldSize = list.size();
        this.bookService.delete("1");
        Assert.assertEquals(oldSize - 1, this.bookService.findAll().size());
    }
}
