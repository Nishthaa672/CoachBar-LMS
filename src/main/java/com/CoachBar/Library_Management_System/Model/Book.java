package com.CoachBar.Library_Management_System.Model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;



@Entity
public class Book {
	
	public Book( String title,
			String author,
			int publicationYear) {
		super();
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
	}
	public Book() {
		super();
	}
	public Book(long id,String title,
			String author,
			int publicationYear) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="Title is mandatory")
	@Column(name="name")
	private String title;
	
	@NotBlank(message="Author is mandatory")
	@Size(max=100,message="Author Name cannot exceed 100 characters")
	private String author;
	
	@Positive(message="Publication year must be positive number")
	@Column(name="publication_year")
	private int publicationYear;

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
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	
}