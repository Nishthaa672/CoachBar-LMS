package com.CoachBar.Library_Management_System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CoachBar.Library_Management_System.Model.Book;
import com.CoachBar.Library_Management_System.Repository.LibraryManagementSystemRepository;

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
	
	public Optional<Book> updateBook(Long id, Book bookDetails) {
        return LMSRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPublicationYear(bookDetails.getPublicationYear());
            return LMSRepository.save(book);
        });
    }

}
