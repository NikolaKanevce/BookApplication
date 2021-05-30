# BookApplication
Spring Boot project for books.

The whole application starts when class DemoApplication is runned.<br/>
For running the tests for BookService, should be runned class BookServiceTest in the package service located in package test.<br/>
For running the tests for AuthorService, should be runned class AuthorServiceTest in the package service located in package test.

Routes:<br />
**/api/books** - Get all books.<br />
**/api/books/getAuthors** - Get authors with their books.<br />
**/api/books/add** - Add a new book.<br />
**/api/books/edit/{ISBN}** - Edit a book with the given ISBN.<br />
**/api/books/delete/{ISBN}** - Delete a book with the given ISBN.<br />
**/api/books/sortedByYear** - Get books sorted by year.<br />
**/api/books/getBooksByFirstLetterOfAuthor/{letter}** - Get a books from authors whose surnames start with the given letter.<br />
**/api/books/oldestAndNewest** - Get oldest and newest book.<br />
**/api/books/authorsWithSameDecadeAsBooks** - Get authors that are born in the same decade with the decade that at least one existing book is published in.<br />
**/api/books/getAuthorsWithMoreThanThreeBooks** - Get authors that have written more than 3 books.<br />

<strong>Application starts on port 8080.<strong/>
