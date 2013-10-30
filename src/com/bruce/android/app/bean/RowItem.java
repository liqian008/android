package com.bruce.android.app.bean;

public class RowItem {

	private String title;
	private String author;
	private String date;
	private int count;
	
	public RowItem(){
		super();
	}

	public RowItem(String title, String author, String date, int count){
		super();
		this.title = title;
		this.author = author;
		this.date = date;
		this.count = count;
	}

	public String getTitle() {
		return title;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	@Override
	public String toString() {
		return title +"-" +author +"-"+ date +"-"+count;
	}

}
