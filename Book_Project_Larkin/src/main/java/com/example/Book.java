package com.example;

public class Book {

	private String isbn;
	private String title;
	private String author;
	private String cover;
	private String detail;

	public Book(String isbn, String title, String author, String src, String detail) {
		super();
		this.detail=detail;
		this.cover=src;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}
	
	public String getDetail(){
		return detail;
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
	
	public void setDetail(String detail){
		this.detail = detail;
	}
	
	public void setCover(String cover){
		this.cover=cover;
	}
	
	public String getCover(){
		return cover;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + "]";
	}
	
}
