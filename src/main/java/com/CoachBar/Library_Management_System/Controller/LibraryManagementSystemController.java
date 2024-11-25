package com.CoachBar.Library_Management_System.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CoachBar.Library_Management_System.Model.Book;
import com.CoachBar.Library_Management_System.Service.LibraryManagementSystemService;

@RestController
@RequestMapping("/api/lms")
public class LibraryManagementSystemController {
	
	private LibraryManagementSystemService LMSService;
	
	public LibraryManagementSystemController(LibraryManagementSystemService LMSService)
	{
		this.LMSService=LMSService;
	}
	
	@GetMapping("/books")
	public List<Book> getAllBooks()
	{
		return LMSService.getAllBooks();
	}
	
	@GetMapping("/books/{id}")
	public Optional<Book> getBookById(@PathVariable Long id)
	{
		return LMSService.getBookById(id);
	}

}
