 package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBookRepository implements BookRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Book> findAll() {
		String sql = "SELECT * FROM books";
		return jdbcTemplate.query(sql, new BookRowMapper());
	}

	@Override
	public void delete(String isbn) {
		jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM myBooks WHERE isbn = ?");
	            statement.setString(1, isbn);
	            return statement;
            }
		});
	}

	@Override
	public List<Book> getBook(String isbn) {
		String sql = "SELECT * FROM books WHERE isbn = '"+isbn+"'";
		return jdbcTemplate.query(sql, new BookRowMapper());
	}

	@Override
	public void add(Book book) {
		List<Book> b = getBook(book.getIsbn());
		if(b.size()==0){
			jdbcTemplate.update(new PreparedStatementCreator() {
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	
		            PreparedStatement statement = connection.prepareStatement("INSERT INTO books (isbn, title, author, src, detail) VALUES (?, ?, ?, ?, ?)");
		            statement.setString(1, book.getIsbn());
		            statement.setString(2, book.getTitle());
		            statement.setString(3, book.getAuthor());
		            statement.setString(4, book.getCover());
		            statement.setString(5, book.getDetail());
		            
		
		            return statement;
	            }
			});
		}
	}

	@Override
	public void addToMybooks(Book book) {
		List<Book> b = findP(book.getIsbn());
		if(b.size()==0){
			jdbcTemplate.update(new PreparedStatementCreator() {
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	
		            PreparedStatement statement = connection.prepareStatement("INSERT INTO myBooks (isbn, title, author, src, detail) VALUES (?, ?, ?, ?, ?)");
		            statement.setString(1, book.getIsbn());
		            statement.setString(2, book.getTitle());
		            statement.setString(3, book.getAuthor());
		            statement.setString(4, book.getCover());
		            statement.setString(5, book.getDetail());
		            
		
		            return statement;
	            }
			});
		}
	}

	@Override
	public List<Book> findMyBooks() {
		String sql = "SELECT * FROM myBooks";
		return jdbcTemplate.query(sql, new BookRowMapper());
	}
	
	public List<Book> findP(String isbn){
		String sql = "SELECT * FROM myBooks WHERE isbn = '"+isbn+"'";
		return jdbcTemplate.query(sql, new BookRowMapper());
	}

	@Override
	public void addReview(String isbn, String review) {
		jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO reviews (isbn, review) VALUES (?, ?)");
	            statement.setString(1, isbn);
	            statement.setString(2, review);
	            return statement;
            }
		});
		
	}

	@Override
	public List<String> getReviews(String isbn) {
		String sql = "SELECT review FROM reviews WHERE isbn = '"+isbn+"'";
		return jdbcTemplate.query(sql, new ReviewRowMapper());
	}

	
}
