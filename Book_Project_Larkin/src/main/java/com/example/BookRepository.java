package com.example;

import java.util.List;


public interface BookRepository {

	List<Book> findAll();
	
	List<Book> findMyBooks();
	
	void delete(String isbn);
	
	List<Book> getBook(String isbn);
	
	void add(Book book);
	
	void addToMybooks(Book book);

	List<Book> findP(String isbn);
	
	void addReview(String isbn, String review);
	
	List<String> getReviews(String isbn);
	
}
