package com.app.book.controller;

import com.app.book.entity.Book;
import com.app.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            HttpStatus httpStatus = books.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
            return new ResponseEntity<>(books, httpStatus);
        } catch (Exception exc) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book bookVal = bookRepository.save(book);

        return new ResponseEntity<>(bookVal, HttpStatus.OK);
    }

    @PostMapping("/updateBookById/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBook) {

        Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent()) {
            Book oldBook = book.get();
            oldBook.setBookTitle(newBook.getBookTitle());
            oldBook.setAuthorName(newBook.getAuthorName());
            oldBook.setPublishedDate(newBook.getPublishedDate());

            Book updatedBook = bookRepository.save(oldBook);

            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
        bookRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
