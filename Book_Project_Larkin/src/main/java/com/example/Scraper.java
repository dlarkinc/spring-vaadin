package com.example;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {
	
	ArrayList<Book> booklist;
	
	public Scraper(){
		booklist = new ArrayList<Book>();
		parse();
	}
	
	public void parse(){
		Document doc;
		try {
			doc = Jsoup.connect("https://www.goodreads.com/giveaway").get();
			Elements books = doc.select(".bookCover");
			Elements books_details = doc.select(".giveawayDescriptionDetails");
			String delim = " by ";
			for(int i = 0; i<books.size();i++){
				Element detail_temp = books_details.get(i).child(1);
				String detail_text = detail_temp.text();
				String x = books.get(i).attr("title");
				int index = x.lastIndexOf(delim);
				String title = x.substring(0, index);
				String author = x.substring(index+4, x.length());
				String  src = books.get(i).attr("src");
				Book b = new Book("0000"+i,title,author,src, detail_text);
				b.setCover(src);
				booklist.add(b);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Book> getBooks(){
		return booklist;
	}
}
