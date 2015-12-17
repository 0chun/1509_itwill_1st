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
	
	//true : ����Ȱ�
	//false: ���� �ȵȰ�

	// �뿩
	public boolean rentBook(Rental rental) throws Exception {
	
		boolean joinOK = false;
		boolean isRent = bookDao.isRent(rental.getRentalBookNo());
		if (isRent == false) {	//������ �ȵǾ��ִ� ���
			rentalDao.add(rental);
			isRentUpdate(rental, isRent);
			joinOK = true;
		}
		return joinOK;
	}
	
	//true : ����Ȱ�
	//false: ���� �ȵȰ�
	
	// �ݳ�.
	public boolean returnBook(Rental rental) throws Exception {

		boolean delete = false;
		boolean isRent = bookDao.isRent(rental.getRentalBookNo());
		if(isRent == true){	// ������ �Ǿ� �ִ� ���
			rentalDao.delete(rental.getRentalBookNo());
			isRentUpdate(rental, !delete);
			delete = true;
		}
		
		return delete;
	}
	
	// isRent �� ����
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
	 * ȸ����ȣ��ȸ
	 */
	public Rental findByNo(int no) throws Exception {
		Rental rental = rentalDao.readByNo(no);
		return rental;
	}

	// * ��ü����Ʈ

	public ArrayList<Rental> listOfRental() throws Exception {
		ArrayList<Rental> rentalList = rentalDao.readAll();
		return rentalList;
	}
	
	/* ȸ������
	 
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
