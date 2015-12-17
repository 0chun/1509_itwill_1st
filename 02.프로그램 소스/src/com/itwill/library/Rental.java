package com.itwill.library;

import java.io.Serializable;

public class Rental implements Serializable{
	private int rentalBookNo;
	private int rentalMemberNo;
	
	// ���⳯¥ : private long rentalDate
	// �ݳ���¥ : private long returnDate
	public Rental() {
		// TODO Auto-generated constructor stub
	}

	public Rental(int rentalBookNo,int rentalMemberNo) {
		super();
		this.rentalBookNo = rentalBookNo;
		this.rentalMemberNo = rentalMemberNo;
	}

	public int getRentalMemberNo() {
		return rentalMemberNo;
	}

	public void setRentalMemberNo(int rentalMemberNo) {
		this.rentalMemberNo = rentalMemberNo;
	}

	public int getRentalBookNo() {
		return rentalBookNo;
	}

	public void setRentalBookNo(int rentalBookNo) {
		this.rentalBookNo = rentalBookNo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return rentalBookNo + "                "+rentalMemberNo;
	}
	
	public void print(){
		System.out.println(rentalBookNo + "\t" + rentalMemberNo);
	}

}