package com.example;

import java.util.List;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class NavBar extends VerticalLayout{
	
	Label title;
	VerticalLayout vl;
	
	public NavBar(){
		title = new Label("My Books");
		vl = new VerticalLayout();
		vl.addComponent(title);
		addComponent(vl);    
	}
	
	public void Refresh(List<Book> books, BookRepository bookRepo){
		removeComponent(vl);
		vl = new VerticalLayout();
		vl.addComponent(title);
		for(int i = 0; i < books.size();i++){
			myBookUI ui = new myBookUI(books.get(i),true,bookRepo);
			ui.setBar(this);
			vl.addComponent(ui);
		}
        addComponent(vl);    
	}

}
