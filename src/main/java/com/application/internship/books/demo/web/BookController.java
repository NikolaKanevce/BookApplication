package com.application.internship.books.demo.web;

import com.application.internship.books.demo.model.Author;
import com.application.internship.books.demo.model.Book;
import com.application.internship.books.demo.model.dto.BookDto;
import com.application.internship.books.demo.service.AuthorService;
import com.application.internship.books.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping
    public List<Book> findAll(){
        return this.bookService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{ISBN}")
    public ResponseEntity<Book> edit(@PathVariable String ISBN, @RequestBody BookDto bookDto) {
        return this.bookService.edit(ISBN, bookDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{ISBN}")
    public ResponseEntity<Book> delete(@PathVariable String ISBN){
        this.bookService.delete(ISBN);
        if(this.bookService.findByISBN(ISBN).isEmpty()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getAuthors")
    public Map<Author, List<Book>> getAuthorsWithBooks(){
        return this.authorService.getAuthorsWithBooks();
    }

    @GetMapping("/sortedByYear")
    public List<Book> getSortedBooksByYear(){
        return this.bookService.getBooksSortedByYear();
    }

    @GetMapping("/getBooksByFirstLetterOfAuthor/{letter}")
    public List<Book> getSortedBooksByYear(@PathVariable String letter){
        return this.bookService.getBooksByFirstLetterOfTheAuthorSurname(letter);
    }

    @GetMapping("/oldestAndNewest")
    public List<Book> getOldestAndNewestBook(){
        return this.bookService.getOldestAndNewestBook();
    }

    @GetMapping("/authorsWithSameDecadeAsBooks")
    public List<Author> authorsWithSameDecadeAsBooks() {
        return this.authorService.authorsWithSameDecadeAsBooks();
    }

    @GetMapping("/getAuthorsWithMoreThanThreeBooks")
    public List<Author> getAuthorsWithMoreThanThreeBooks() {
        return this.authorService.getAuthorsWithMoreThanThreeBooks();
    }

}
