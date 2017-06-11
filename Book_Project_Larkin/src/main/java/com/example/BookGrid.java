package com.example;

import java.util.List;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class BookGrid extends GridLayout{
	
	BookGrid this_grid = this;
	GridLayout booklayout;
	BookRepository bookRepo;
	List<Book> books;
	int counter = 0;
	NavBar bar;
	
	public BookGrid(List<Book> books, BookRepository bookRepo, NavBar bar){
		this.bar = bar;
		this.bookRepo = bookRepo;
		this.books = books;
		booklayout = new GridLayout();
		booklayout.addStyleName("outlined");
		booklayout.setSizeFull();
		int length = books.size();
		int rows = 0;
		if (length > 3){
			rows = length / 3;
		}
		length = length - (rows * 3);
		if (length > 0){
			rows++;
		}
	    generateMatrixGrid(rows, 3);
	    addComponent(booklayout);   
	}
	
	private void generateMatrixGrid(final int rows, final int columns) {
		booklayout.removeAllComponents();
		booklayout.setRows(rows);
		booklayout.setColumns(columns);
        for (int row = 0; row < booklayout.getRows(); row++) {
            for (int col = 0; col < booklayout.getColumns(); col++) {
            	if(counter < books.size()){
            		Book tempB = books.get(counter);
	            	BookUI layout = new BookUI(tempB);
	                booklayout.addComponent(layout);     
	                layout.addLayoutClickListener(event -> {
	                    ModalDescript sub = new ModalDescript(tempB, bookRepo, bar);

	                    // Add it to the root component
	                    UI.getCurrent().addWindow(sub);
	                });
	                booklayout.setRowExpandRatio(row, 0.0f);
	                booklayout.setColumnExpandRatio(col, 0.0f);
            	}
            	counter++;
            }
        }
    }

}
