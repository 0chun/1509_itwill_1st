package com.itwill.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
Dao(Data Access Object)
	- Manager에서 회원의 파일정보에(Data) 접근하기위해서 사용하는객체
	- 멤버데이타를 저장하고 있는 파일(Data) 에 쓰기,읽기,수정,삭제를 하는객체
	- RandomAccessFile을 이용한 파일 영속성 처리
*/
public class RentalDao {
	public RentalDao() throws Exception {
		File file = new File("rental.ser");
		if(!file.exists()){
			file.createNewFile();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(new ArrayList<Rental>());//최초 한번만 실행 => 초기화 작업
			oos.close();
		}
	}

	private ArrayList<Rental> open() throws Exception {

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("rental.ser"));
		ArrayList<Rental> rentalList = (ArrayList<Rental>)ois.readObject();
		ois.close();
		return rentalList;
		
	}

	private void close(ObjectInputStream ois) throws Exception {
		ois.close();
	}

	private void write(ArrayList<Rental> rentalList) throws Exception{//object 데이터를 쓰는
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("rental.ser"));
		oos.writeObject(rentalList);
		oos.close();
	}

	public void add(Rental rental) throws Exception{//저장
		ArrayList<Rental> rentalList = this.open();
		rentalList.add(rental);
		this.write(rentalList);
	}

	public Rental readByNo(int no) throws Exception{
		Rental rental = null;
		ArrayList<Rental> rentalList = open();
		for (int i = 0; i < rentalList.size(); i++) {
			Rental tempRental = rentalList.get(i);
			if (tempRental.getRentalBookNo()==no) {
				rental = tempRental;
				break;
			}
		}
		return rental;
	}

	public ArrayList<Rental> readAll() throws Exception {
		ArrayList<Rental> rentalList = open();
		return rentalList;

	}

	public Rental update(Rental rental) throws Exception {
		Rental rental1 = null;
		ArrayList<Rental> rentalList = open();
		
		for (int i = 0; i < rentalList.size(); i++) {
			if(rentalList.get(i).getRentalBookNo()==rental1.getRentalBookNo()){
				rental1 = rentalList.set(i, rental1);
				this.write(rentalList);//얘 필수..!!!! 다시써야대
			}
		}return rental1;
	}

	public Rental delete(int no) throws Exception {
		Rental rental = null;
		ArrayList<Rental> rentalList = open();
		
		for (int i = 0; i < rentalList.size(); i++) {
			if(rentalList.get(i).getRentalBookNo()==no){
				rental = rentalList.remove(i);
				this.write(rentalList);//전체로 들어간다.
			}
		}return rental;
	}
}
