package AseFirstApplication.com.acc.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import AseFirstApplication.com.acc.entities.BookEntity;
import AseFirstApplication.com.acc.exception.ResourceNotFoundException;
import AseFirstApplication.com.acc.repositories.BookRepository;


@RestController
@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List <BookEntity> getAllBookEntitys() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity < BookEntity > getBookEntityById(@PathVariable(value = "id") Long bookId)
    throws ResourceNotFoundException {
        BookEntity book = bookRepository.findById(bookId)
            .orElseThrow(() -> new ResourceNotFoundException("BookEntity not found for this id :: " + bookId));
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/books")
    public BookEntity createBookEntity(@Valid @RequestBody BookEntity book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity < BookEntity > updateBookEntity(@PathVariable(value = "id") Long bookId,
        @Valid @RequestBody BookEntity bookDetails) throws ResourceNotFoundException {
        BookEntity book = bookRepository.findById(bookId)
            .orElseThrow(() -> new ResourceNotFoundException("BookEntity not found for this id :: " + bookId));

        book.setName(bookDetails.getName());
        book.setAuthorName(bookDetails.getAuthorName());
        book.setPrice(bookDetails.getPrice());
        final BookEntity updatedBookEntity = bookRepository.save(book);
        return ResponseEntity.ok(updatedBookEntity);
    }

    @DeleteMapping("/books/{id}")
    public Map < String, Boolean > deleteBookEntity(@PathVariable(value = "id") Long bookId)
    throws ResourceNotFoundException {
        BookEntity book = bookRepository.findById(bookId)
            .orElseThrow(() -> new ResourceNotFoundException("BookEntity not found for this id :: " + bookId));

        bookRepository.delete(book);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}