package com.itwill.library;

import java.io.Serializable;
import java.util.ArrayList;

public class BookDaoMain {

	public static void main(String[] args) throws Exception {

			BookDao bd = new BookDao();
/*			bd.add(new Book(1,"���� ��ȭ�������","��ȫ��","â��","����",false));
			bd.add(new Book(2,"�� �д� �� �ù�","�ϻ��","����","��",false));
			bd.add(new Book(3,"������","������","�������÷���","������",false));
			bd.add(new Book(4,"���� �ܾ�","�ڿ���","���Ͽ콺","�ι�",false));
			bd.add(new Book(5,"���","����","ȫ�����ǻ�","�ι�",false));
			bd.add(new Book(6,"���̾�","�츣���켼","������","�Ҽ�",false));
			bd.add(new Book(7,"���ݼ���","�Ŀ�� �ڿ���","���е���","�Ҽ�",false));
			bd.add(new Book(8,"��������","���� ����","������","�Ҽ�",false));*/
			
			
			
			
			
//			Book list=bd.readByNo(1);
//			System.out.println(list);
			
			System.out.println("-----BookList------");
			ArrayList<Book> bookList= bd.readAll();
			for (Book book : bookList) {
				System.out.println(book);
			}
				
			
			
			
	}

}
