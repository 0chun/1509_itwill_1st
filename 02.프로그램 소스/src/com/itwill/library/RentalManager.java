package com.itwill.library;

import java.util.ArrayList;

public class RentalManager {

	RentalDao rentalDao;
	MemberDao memberDao;
	BookDao bookDao;
	BookManager bookManager;
	
	ArrayList<Book> booklist;

	public RentalManager() throws Exception {
		memberDao = new MemberDao();
		rentalDao = new RentalDao();
		bookDao = new BookDao();
		bookManager = new BookManager();
	}
	
	//true : 대출된거
	//false: 대출 안된거

	// 대여
	public boolean rentBook(Rental rental) throws Exception {
	
		boolean joinOK = false;
		boolean isRent = bookDao.isRent(rental.getRentalBookNo());
		if (isRent == false) {	//대출이 안되어있는 경우
			rentalDao.add(rental);
			isRentUpdate(rental, isRent);
			joinOK = true;
		}
		return joinOK;
	}
	
	//true : 대출된거
	//false: 대출 안된거
	
	// 반납.
	public boolean returnBook(Rental rental) throws Exception {

		boolean delete = false;
		boolean isRent = bookDao.isRent(rental.getRentalBookNo());
		if(isRent == true){	// 대출이 되어 있는 경우
			rentalDao.delete(rental.getRentalBookNo());
			isRentUpdate(rental, !delete);
			delete = true;
		}
		
		return delete;
	}
	
	// isRent 값 변경
	private void isRentUpdate(Rental rental, boolean isRent) throws Exception {
		booklist = bookManager.listOfBook();
		for (int i = 0; i < booklist.size(); i++) {
			if(booklist.get(i).getBookNo() == rental.getRentalBookNo()){
				booklist.get(i).setIsRent(!isRent);
				bookDao.write(booklist);
				break;
			}
		}
		
	}
	
	/*
	 * 회원번호조회
	 */
	public Rental findByNo(int no) throws Exception {
		Rental rental = rentalDao.readByNo(no);
		return rental;
	}

	// * 전체리스트

	public ArrayList<Rental> listOfRental() throws Exception {
		ArrayList<Rental> rentalList = rentalDao.readAll();
		return rentalList;
	}
	
	/* 회원수정
	 
	public boolean findByUpdate(Rental rental) throws Exception {
		boolean update = false;
		Rental rental2 = rentalDao.update(rental);
		if (rental2 == null) {
			rentalDao.update(rental2);
			update = true;
		} else {

		}
		return update;
	}
*/
}
