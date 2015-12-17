package com.itwill.library;

import java.util.ArrayList;

public class BookManager {
	BookDao bookDao;

	public BookManager() throws Exception {
		bookDao = new BookDao();
	}

	/*
	 * �����߰�
	 */
	public boolean addBook(Book book) throws Exception {
		boolean bookAdd = false;
		// 1. å ��ȿ��üũ
		Book getBook = bookDao.readByNo(book.getBookNo());
		// 2. å �߰�
		if (getBook == null) {
			bookDao.add(book);
			bookAdd = true;
		}
		return bookAdd;
	}

	/*
	 * ��ȣ�ΰ˻�
	 */
	public Book findByNo(int no) throws Exception {
		Book book = bookDao.readByNo(no);
		return book;
	}

	/*
	 * �̸����ΰ˻�
	 */
	public Book findByName(String name) throws Exception {
		Book book = bookDao.readByName(name);
		return book;
	}

	/*
	 * ������ü��ȸ
	 */
	public ArrayList<Book> listOfBook() throws Exception {
		ArrayList<Book> book = bookDao.readAll();
		return book;
	}

	/*
	 * ��������
	 */
	public boolean updateBook(Book book) throws Exception {
		Book getBook = bookDao.update(book);
		boolean update = false;
		if (book == null) {
			bookDao.update(book);
			update = true;
		} else {
		}
		return update;
	}

	/*
	 * ���� ����
	 */
	public boolean deleteBook(int bookNo) throws Exception {
		Book getBook = bookDao.delete(bookNo);
		boolean delete = false;
		if (getBook == null) {
			bookDao.delete(bookNo);
			delete = true;
		} else {
		}
		return delete;
	}

}
