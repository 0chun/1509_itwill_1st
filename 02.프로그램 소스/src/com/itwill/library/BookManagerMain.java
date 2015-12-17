package com.itwill.library;

import java.util.ArrayList;

public class BookManagerMain {

	public static void main(String[] args) throws Exception {
		BookManager bmg = new BookManager();
		// 도서추가 테스트
		System.out.println("-------도서추가-------");
		boolean add = bmg.addBook(new Book(10, "토익", "몰라1", "몰라2", "몰라3", true));
		System.out.println(add);
		// 전체 리스트
		System.out.println("-------전체 리스트------");
		ArrayList<Book> allList = bmg.listOfBook();
		System.out.println(allList);
		// 번호로 검색
		System.out.println("--------번호로 검색---------");
		Book findNumber = bmg.findByNo(10);
		System.out.println(findNumber);

		//삭제 테스트
		System.out.println("-------삭제------");
		boolean del=bmg.deleteBook(1);
		System.out.println(del);

	}

}
