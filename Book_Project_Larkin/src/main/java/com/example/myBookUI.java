package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.PopupView.Content;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public class myBookUI extends VerticalLayout {
	
	HorizontalLayout hl;
	HorizontalLayout h2l;
	VerticalLayout vl;
	Image img;
	Label title;
	Label author;
	Book book;
	PopupView pop;
	NavBar bar;
	BookRepository bookRepo;
	
	
	public myBookUI(Book book, boolean deleteVal, BookRepository bookRepo)
	{
		this.bookRepo = bookRepo;
		this.book = book;
		img = new Image();
		img.setSource(new ExternalResource(book.getCover()));
		title = new Label(book.getTitle());
		title.addStyleName("h4");
		author = new Label(book.getAuthor());
		author.addStyleName("h5");
		Button but = new Button("Delete");
		
		pop = new PopupView(new PopupTextFieldContent());
		
		vl = new VerticalLayout();
		hl = new HorizontalLayout();
		h2l = new HorizontalLayout();
		hl.addComponent(img);
		vl.addComponent(title);
		vl.addComponent(author);
		if(deleteVal == true){
			h2l.addComponent((new Button("Delete", event -> {
				bookRepo.delete(book.getIsbn());
				bar.Refresh(bookRepo.findMyBooks(), bookRepo);
	        })));
			h2l.addComponent(pop);
			vl.addComponent(h2l);
		}
		hl.addComponent(vl);
		addComponent(hl);    
	}
	
	public void setBar(NavBar bar){this.bar=bar;}
	
	public Book getBook(){
		return book;
	}
	
	class PopupTextFieldContent implements PopupView.Content {
	    private final VerticalLayout layout;
	    private final TextField textField = new TextField("Review Content");
	    private final Button review = new Button("Submit");

	    private PopupTextFieldContent() {
	    	textField.setMaxLength(80);
	    	textField.setPlaceholder("'Good Read'");
	        layout = new VerticalLayout();
	        layout.addComponent(textField);
	        layout.addComponent(review);
	        review.addClickListener(event -> {
	        	bookRepo.addReview(book.getIsbn(), textField.getValue());
	        	Notification.show("Review Submitted:",book.getTitle() + " : " + textField.getValue(),Type.TRAY_NOTIFICATION);
            });
	    }

	    @Override
	    public final Component getPopupComponent() {
	        return layout;
	    }

	    @Override
	    public final String getMinimizedValueAsHTML() {
	        return new String("<Button>Review</Button>");
	    }
	}


}
