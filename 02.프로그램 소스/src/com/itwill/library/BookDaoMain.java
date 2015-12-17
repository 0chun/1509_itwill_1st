package com.itwill.library;

import java.io.Serializable;
import java.util.ArrayList;

public class BookDaoMain {

	public static void main(String[] args) throws Exception {

			BookDao bd = new BookDao();
/*			bd.add(new Book(1,"나의 문화유산답사기","유홍준","창비","역사",false));
			bd.add(new Book(2,"시 읽는 밤 시밤","하상욱","예담","시",false));
			bd.add(new Book(3,"곁으로","김응교","새물결플러스","에세이",false));
			bd.add(new Book(4,"여덟 단어","박웅현","북하우스","인문",false));
			bd.add(new Book(5,"논어","공자","홍익출판사","인문",false));
			bd.add(new Book(6,"데미안","헤르만헤세","민음사","소설",false));
			bd.add(new Book(7,"연금술사","파울로 코엘료","문학동네","소설",false));
			bd.add(new Book(8,"동물농장","조지 오웰","민음사","소설",false));*/
			
			
			
			
			
//			Book list=bd.readByNo(1);
//			System.out.println(list);
			
			System.out.println("-----BookList------");
			ArrayList<Book> bookList= bd.readAll();
			for (Book book : bookList) {
				System.out.println(book);
			}
				
			
			
			
	}

}
