package com.CoachBar.Library_Management_System.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.CoachBar.Library_Management_System.Model.Book;

public interface LibraryManagementSystemRepository extends JpaRepository<Book, Long>{

}
