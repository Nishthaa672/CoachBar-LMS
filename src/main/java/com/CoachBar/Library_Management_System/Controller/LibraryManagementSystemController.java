package com.CoachBar.Library_Management_System.Controller;

import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.CoachBar.Library_Management_System.Model.Book;
import com.CoachBar.Library_Management_System.Service.LibraryManagementSystemService;
import com.CoachBar.Library_Management_System.exception.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lms")
@Validated
public class LibraryManagementSystemController {
	
	private LibraryManagementSystemService LMSService;
	
	public LibraryManagementSystemController(LibraryManagementSystemService LMSService)
	{
		this.LMSService=LMSService;
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = LMSService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK); 
    }
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = LMSService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + id + " not found"));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

	@PostMapping("/book")
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book savedBook = LMSService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookRequest) {
        Book updatedBook = LMSService.updateBook(id, bookRequest);
        if (updatedBook == null) {
            throw new ResourceNotFoundException("Book with ID " + id + " not found");
        }
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
	
	
	@DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        if (!LMSService.deleteBook(id)) {
            throw new ResourceNotFoundException("Book with ID " + id + " not found");
        }
        return new ResponseEntity<>("Object deleted successfully",HttpStatus.OK);
    }
}
