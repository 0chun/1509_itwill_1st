package com.itwill.library;

import java.io.Serializable;
import java.util.Formatter;

public class Book implements Serializable {
	private int bookNo;
	private String bookName;
	private String bookWriter;
	private String bookCompany;
	private String bookType;
	private boolean isRent;

	public Book() {

	}

	public Book(int bookNo, String bookName, String bookWriter, String bookCompany, String bookType, boolean isRent) {

		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookCompany = bookCompany;
		this.bookType = bookType;
		this.isRent = isRent;
	}

	public int getBookNo() {
		return bookNo;
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookWriter() {
		return bookWriter;
	}

	public String getBookCompany() {
		return bookCompany;
	}

	public String getBookType() {
		return bookType;
	}

	public boolean getIsRent() {
		return isRent;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public void setBookName(String booName) {
		this.bookName = booName;
	}

	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}

	public void setBookCompany(String bookCompany) {
		this.bookCompany = bookCompany;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public void setIsRent(boolean isRent) {
		this.isRent = isRent;
	}

	@Override
	public String toString() {
		String BookName    = "              ".substring(bookName.length()) + bookName;
		String BookWriter  = "              ".substring(bookWriter.length()) + bookWriter;
		String BookCompany = "              ".substring(bookCompany.length()) + bookCompany;
		String BookType    = "              ".substring(bookType.length()) + bookType;
		return String.format("%d %20s %20s %20s %20s %10b", bookNo,BookName,BookWriter,BookCompany,BookType,isRent).toString();
	}
	

}
