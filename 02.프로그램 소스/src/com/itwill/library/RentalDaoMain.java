package com.itwill.library;

import java.util.ArrayList;

public class RentalDaoMain {
	public static void main(String[] args) throws Exception {
		RentalDao rentalDao = new RentalDao();
	/*	rentalDao.add(new Rental(1, 2));
		rentalDao.add(new Rental(2, 2));
		rentalDao.add(new Rental(3, 2));
		rentalDao.add(new Rental(4, 2));
		rentalDao.add(new Rental(5, 2));*/
		ArrayList<Rental> rentalList = rentalDao.readAll();
		for (int i = 0; i < rentalList.size(); i++) {
			//System.out.println(rentalList.get(i));
			rentalList.get(i).print();
		}
	}
}
