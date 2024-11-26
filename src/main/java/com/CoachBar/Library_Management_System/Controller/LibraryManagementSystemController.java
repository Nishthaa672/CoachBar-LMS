package com.CoachBar.Library_Management_System.Controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.filters.AddDefaultCharsetFilter.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;

import com.CoachBar.Library_Management_System.Model.Book;
import com.CoachBar.Library_Management_System.Service.LibraryManagementSystemService;
import com.CoachBar.Library_Management_System.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/lms")
public class LibraryManagementSystemController {
	
	private LibraryManagementSystemService LMSService;
	
	public LibraryManagementSystemController(LibraryManagementSystemService LMSService)
	{
		this.LMSService=LMSService;
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = LMSService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK); // 200 OK
    }
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = LMSService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + id + " not found"));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

	@PostMapping("/book")
    public Book createBook(@RequestBody Book book) {
		
		
        return LMSService.createBook(book);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return LMSService.updateBook(id, bookDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
