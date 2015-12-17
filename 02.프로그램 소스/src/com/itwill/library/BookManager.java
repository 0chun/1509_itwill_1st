package com.itwill.library;

import java.util.ArrayList;

public class BookManager {
	BookDao bookDao;

	public BookManager() throws Exception {
		bookDao = new BookDao();
	}

	/*
	 * 도서추가
	 */
	public boolean addBook(Book book) throws Exception {
		boolean bookAdd = false;
		// 1. 책 유효성체크
		Book getBook = bookDao.readByNo(book.getBookNo());
		// 2. 책 추가
		if (getBook == null) {
			bookDao.add(book);
			bookAdd = true;
		}
		return bookAdd;
	}

	/*
	 * 번호로검색
	 */
	public Book findByNo(int no) throws Exception {
		Book book = bookDao.readByNo(no);
		return book;
	}

	/*
	 * 이름으로검색
	 */
	public Book findByName(String name) throws Exception {
		Book book = bookDao.readByName(name);
		return book;
	}

	/*
	 * 도서전체조회
	 */
	public ArrayList<Book> listOfBook() throws Exception {
		ArrayList<Book> book = bookDao.readAll();
		return book;
	}

	/*
	 * 도서수정
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
	 * 도서 삭제
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
