package com.CoachBar.Library_Management_System.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;


@Entity
public class LibraryManagementSystemModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="Title is mandatory")
	private String title;
	
	@NotBlank(message="Author is mandatory")
	private String author;
	
	@NotBlank(message="Publication Year is mandatory")
	@PastOrPresent(message="Date must be in the past or present")
	private LocalDate publicationYear;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public LocalDate getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(LocalDate publicationYear) {
		this.publicationYear = publicationYear;
	}
	
}
