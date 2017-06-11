package com.example;

import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public class ModalDescript extends Window {

	public ModalDescript(Book book, BookRepository bookRepo, NavBar bar) {
        super(book.getTitle()); // Set window caption
        
        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
       
        myBookUI bookui = new myBookUI(book,false,bookRepo);
        TextArea body = new TextArea("Description");
        body.setWordWrap(true); // The default
        body.setValue(book.getDetail());
        body.setSizeFull();
        center();
        
        // Disable the close button
        setClosable(false);
        
        vl.addComponent(bookui);
        vl.addComponent(body);
        List<String> reviews = bookRepo.getReviews(book.getIsbn());
        if(reviews.size() >0){
        	Label reviewH = new Label("Reviews");
        	reviewH.addStyleName("h4");
        	vl.addComponent(reviewH);
        	for(String review : reviews){
        		vl.addComponent(new Label(review));
            }
        }
        hl.addComponent(new Button("Add Book", event -> {
        	bookRepo.addToMybooks(book);
			bar.Refresh(bookRepo.findMyBooks(), bookRepo);
			Notification.show("Added Book:",book.getTitle(),Type.TRAY_NOTIFICATION);
			close();
        }));
        hl.addComponent(new Button("Close me", event -> close()));
        vl.addComponent(hl);
        setContent(vl);
    }
	
}
