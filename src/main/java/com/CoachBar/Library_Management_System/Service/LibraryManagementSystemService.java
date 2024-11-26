package com.CoachBar.Library_Management_System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CoachBar.Library_Management_System.Model.Book;
import com.CoachBar.Library_Management_System.Repository.LibraryManagementSystemRepository;

import jakarta.validation.Valid;

@Service
public class LibraryManagementSystemService {
	
	private LibraryManagementSystemRepository LMSRepository;
	
	public LibraryManagementSystemService(LibraryManagementSystemRepository LMSRepository)
	{
		this.LMSRepository=LMSRepository;
	}
	
	public List<Book> getAllBooks()
	{
		return LMSRepository.findAll();
	}

	
	public Optional<Book> getBookById(Long id)
	{
		return LMSRepository.findById(id);
	}
	
	public Book createBook(Book book) {
        return LMSRepository.save(book);
    }
	
	public Book updateBook(Long id, @Valid Book book) {
        if (!LMSRepository.existsById(id)) {
            return null; 
        }
        book.setId(id);
        return LMSRepository.save(book);
    }
	
	
	public boolean deleteBook(Long id) {
        if (LMSRepository.existsById(id)) {
            LMSRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
