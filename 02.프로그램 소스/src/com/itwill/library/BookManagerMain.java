package com.itwill.library;

import java.util.ArrayList;

public class BookManagerMain {

	public static void main(String[] args) throws Exception {
		BookManager bmg = new BookManager();
		// �����߰� �׽�Ʈ
		System.out.println("-------�����߰�-------");
		boolean add = bmg.addBook(new Book(10, "����", "����1", "����2", "����3", true));
		System.out.println(add);
		// ��ü ����Ʈ
		System.out.println("-------��ü ����Ʈ------");
		ArrayList<Book> allList = bmg.listOfBook();
		System.out.println(allList);
		// ��ȣ�� �˻�
		System.out.println("--------��ȣ�� �˻�---------");
		Book findNumber = bmg.findByNo(10);
		System.out.println(findNumber);

		//���� �׽�Ʈ
		System.out.println("-------����------");
		boolean del=bmg.deleteBook(1);
		System.out.println(del);

	}

}
