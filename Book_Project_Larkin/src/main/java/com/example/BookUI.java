package com.example;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


@SuppressWarnings("serial")
public class BookUI extends VerticalLayout {
	
	//image
	//title
	//author
	VerticalLayout vl;
	Image img;
	Label title;
	Label author;
	
	
	
	public BookUI(Book book)
	{
		img = new Image();
		img.setSource(new ExternalResource(book.getCover()));
		title = new Label(book.getTitle());
		title.setWidth("220px");
		title.addStyleName("h4");
		author = new Label(book.getAuthor());
		author.addStyleName("h4");
		vl = new VerticalLayout();
		vl.addComponent(img);
		vl.addComponent(title);
		vl.addComponent(author);
		addComponent(vl);    
	}


}
