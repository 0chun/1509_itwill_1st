package com.itwill.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BookDao {

	public BookDao() throws Exception {
		File file = new File("book.ser");
		if (!file.exists()) {
			file.createNewFile();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(new ArrayList<Book>());
			oos.close();
		}
	}

	// open
	private ArrayList<Book> open() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("book.ser"));
		ArrayList<Book> bookList = (ArrayList<Book>) ois.readObject();
		ois.close();
		return bookList;
	}

	private void close(ObjectInputStream ois) throws Exception {
		ois.close();
	}

	// write
	public void write(ArrayList<Book> bookList) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Book.ser"));
		oos.writeObject(bookList);
		oos.close();
	}

	// create
	public void add(Book book) throws Exception {
		ArrayList<Book> bookList = this.open();
		bookList.add(book);
		this.write(bookList);
	}

	// read
	public Book readByNo(int no) throws Exception {
		Book book = null;
		ArrayList<Book> bookList = open();
		for (int i = 0; i < bookList.size(); i++) {
			Book tempbook = bookList.get(i);
			if (tempbook.getBookNo() == no) {
				book = tempbook;
				break;
			}
		}
		return book;
	}

	// read
	public Book readByName(String name) throws Exception {
		Book book = null;
		ArrayList<Book> bookList = open();
		for (int i = 0; i < bookList.size(); i++) {
			Book tempbook = bookList.get(i);
			if (tempbook.getBookName().equals(name)) {
				book = tempbook;
				break;
			}
		}
		return book;
	}

	// read
	public ArrayList<Book> readAll() throws Exception {
		ArrayList<Book> bookList = open();
		return bookList;
	}

	// update
	public Book update(Book book) throws Exception {
		Book book2 = null;
		ArrayList<Book> bookList = open();

		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getBookNo() == book.getBookNo()) {
				book2 = bookList.set(i, book);
				this.write(bookList);
			}
		}
		return book2;
	}

	// delete
	public Book delete(int no) throws Exception {
		Book book = null;
		ArrayList<Book> bookList = open();
		for (int i = 0; i < bookList.size(); i++) {
			Book tempbook = bookList.get(i);
			if (tempbook.getBookNo() == no) {
				book = bookList.remove(i);
				this.write(bookList);
				break;
			}
		}
		return book;
	}

	// 대출여부 수정
	public boolean isRent(int no) throws Exception {
		boolean rent = false;
		Book book = readByNo(no);
		if (book != null) {
			rent = book.getIsRent();
		}
		return rent;
	}

}
