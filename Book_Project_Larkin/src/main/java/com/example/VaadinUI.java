package com.example;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	@Autowired
	BookRepository bookRepo;
	Label test;
	BookGrid bookgrid;
	HorizontalLayout hl;
	VerticalLayout vl;
	NavBar bar;
	static Boolean boo = false;
	
	public VaadinUI() {
		bar = new NavBar();
		test= new Label("Available Books - Next change in 12 hours");
		test.setStyleName("h1");
		hl = new HorizontalLayout();
		vl = new VerticalLayout();
	}
	
	@Override
	protected void init(VaadinRequest request) {
		Scraper scraped_books = new Scraper();
		for(int i = 0; i < scraped_books.getBooks().size();i++){
			bookRepo.add(scraped_books.getBooks().get(i));
		}
		List<Book> books = bookRepo.findAll();
		bookgrid = new BookGrid(books, bookRepo, bar);
		bookgrid.setWidth("75%");
		bar.setWidth("25%");
		vl.addComponent(test);
		vl.addComponent(new Label("<hr>", ContentMode.HTML));
		vl.addComponent(bookgrid);
		hl.addComponent(vl);
		hl.addComponent(bar);
		setContent(hl);
	}
}
