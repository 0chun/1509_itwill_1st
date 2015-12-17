package com.itwill.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
Dao(Data Access Object)
	- Manager���� ȸ���� ����������(Data) �����ϱ����ؼ� ����ϴ°�ü
	- �������Ÿ�� �����ϰ� �ִ� ����(Data) �� ����,�б�,����,������ �ϴ°�ü
	- RandomAccessFile�� �̿��� ���� ���Ӽ� ó��
*/
public class RentalDao {
	public RentalDao() throws Exception {
		File file = new File("rental.ser");
		if(!file.exists()){
			file.createNewFile();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(new ArrayList<Rental>());//���� �ѹ��� ���� => �ʱ�ȭ �۾�
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

	private void write(ArrayList<Rental> rentalList) throws Exception{//object �����͸� ����
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("rental.ser"));
		oos.writeObject(rentalList);
		oos.close();
	}

	public void add(Rental rental) throws Exception{//����
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
				this.write(rentalList);//�� �ʼ�..!!!! �ٽý�ߴ�
			}
		}return rental1;
	}

	public Rental delete(int no) throws Exception {
		Rental rental = null;
		ArrayList<Rental> rentalList = open();
		
		for (int i = 0; i < rentalList.size(); i++) {
			if(rentalList.get(i).getRentalBookNo()==no){
				rental = rentalList.remove(i);
				this.write(rentalList);//��ü�� ����.
			}
		}return rental;
	}
}
