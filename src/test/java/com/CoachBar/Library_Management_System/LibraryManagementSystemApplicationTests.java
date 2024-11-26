package com.CoachBar.Library_Management_System;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.CoachBar.Library_Management_System.Model.Book;
import com.CoachBar.Library_Management_System.Repository.LibraryManagementSystemRepository;
import com.CoachBar.Library_Management_System.Service.LibraryManagementSystemService;
import  org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryManagementSystemApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
	@MockBean
    private LibraryManagementSystemService LMSService;
    
    @SuppressWarnings("removal")
	@MockBean
    private LibraryManagementSystemRepository LMSRepository;
    
   /* @BeforeEach
    public void setup()
    {
    	mockMvc=mockMvc.perform(get("/api/lms/books").with(httpBasic("user","password")));
    }*/
    
    @Test
    @WithMockUser(username="user",password="password",roles= {"USER"})
    public void testGetAllBooks() throws Exception {
       
        Book book1 = new Book(1, "Book One", "Author One", 2020);
        Book book2 = new Book(2, "Book Two", "Author Two", 2021);
        List<Book> books = Arrays.asList(book1, book2);
        Mockito.when(LMSService.getAllBooks()).thenReturn(books);

        
        mockMvc.perform(get("/api/lms/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book One"))
                .andExpect(jsonPath("$[1].author").value("Author Two"));
  
                }
    
    
    
    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void testGetEmployeeByIdSuccess() throws Exception {
    	Book book = new Book(1L, "Book One", "Author One", 2020);
    	
    	Mockito.when(LMSService.getBookById(1L)).thenReturn(Optional.of(book));
        mockMvc.perform(get("/api/lms/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Book One")) 
                .andExpect(jsonPath("$.author").value("Author One")); 
    }
    
    
    
    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void testGetBookByIdNotFound() throws Exception {
        
        Mockito.when(LMSService.getBookById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/lms/books/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Book with ID 1 not found"));
    }
    
    
    
    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void testCreateBook() throws Exception {
        
        
        Book savedBook = new Book("New Book", "New Author", 2022);

        
        Mockito.when(LMSService.createBook(Mockito.any(Book.class))).thenReturn(savedBook);

        
        mockMvc.perform(post("/api/lms/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"New Book\", \"author\": \"New Author\", \"publicationYear\": 2022}"))
                .andExpect(status().isCreated()) 
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.author").value("New Author"));
    }
    
    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void testUpdateBook() throws Exception {
    	Book existingBook = new Book(3, "Book One", "Author One", 2020);
        Book updatedBook = new Book(3,"Updated Book", "Updated Author", 2021);
        
        //Mockito.when(LMSRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        //Mockito.when(LMSRepository.existsById(1L)).thenReturn(true);
        
        Mockito.when(LMSService.updateBook(eq(3L),Mockito.any(Book.class))).thenReturn(updatedBook);
        
        
        mockMvc.perform(put("/api/lms/{id}",3)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Updated Book\", \"author\": \"Updated Author\", \"publicationYear\": 2021}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }

}