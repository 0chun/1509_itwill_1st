package com.itwill.library;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RentalUI extends Frame implements ActionListener {
	BookManager bookManager;
	MemberManager memberManager;
	RentalManager rentalManager;

	List bookList, rentalList;
	Button rentalB, returnB, backB;
	Panel buttonP, listP, NorthP; // 버튼 페널
	
	Label bookNoL, memberNoL;
	TextField bookNoTF, memberNoTF;
	
	TextArea bookTA, rentalTA;
	
	ArrayList<Book> booklist;
	ArrayList<Rental> rentallist;
	

	public RentalUI() throws Exception {

		setTitle("대여/반납관리");

		bookNoL = new Label("책번호");
		memberNoL = new Label("회원번호");
		
		bookNoTF = new TextField(5);
		memberNoTF = new TextField(5);
		
		// 하단 버튼
		rentalB = new Button("대여");
		returnB = new Button("반납");
		backB = new Button("뒤로");
		// 버튼 페널
		buttonP = new Panel();
		buttonP.setLayout(new GridLayout(1,3));
		buttonP.add(rentalB);
		buttonP.add(returnB);
		buttonP.add(backB);
		NorthP = new Panel();
		NorthP.setLayout(new GridLayout(2, 1));
		NorthP.add(bookNoL);
		NorthP.add(bookNoTF);
		NorthP.add(memberNoL);
		NorthP.add(memberNoTF);
		
		// 리스트 패널
		listP = new Panel();
		listP.setLayout(new GridLayout(2, 0));
		listP.add(bookList = new List());
		listP.add(rentalList = new List());

		setLayout(new BorderLayout());
		add(buttonP, BorderLayout.SOUTH);
		add(listP, BorderLayout.CENTER);
		add(NorthP,BorderLayout.NORTH);
		
		// event
		this.addWindowListener(new WindowHandler());
		rentalB.addActionListener(this);
		returnB.addActionListener(this);
		backB.addActionListener(this);
		rentalB.setBackground(Color.pink);
		returnB.setBackground(Color.pink);
		backB.setBackground(Color.pink);
		try {
			bookManager = new BookManager();
			memberManager = new MemberManager();
			rentalManager = new RentalManager();
			booklist = bookManager.listOfBook();
			rentallist = rentalManager.listOfRental();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookList.setVisible(true);
		rentalList.setVisible(true);

		setSize(500, 600);
		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);
		setVisible(true);
		displayBookList(booklist);
		displayRentalList(rentallist);
		
	}
	public void clear() {
		bookNoTF.setText("");
		memberNoTF.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		if (command.equals("대여")) {
			try {
				String vBook = bookNoTF.getText().trim();
				String vMember = memberNoTF.getText().trim();
				
				Rental addRental = new Rental(Integer.parseInt(vBook),Integer.parseInt(vMember));
				boolean rentOK;
				rentOK = rentalManager.rentBook(addRental);
				if (rentOK) {
					JOptionPane.showMessageDialog(this, "대출성공!!!");
					ArrayList<Rental> rentallist = rentalManager.listOfRental();
					ArrayList<Book> booklist = bookManager.listOfBook();
					displayBookList(booklist);
					displayRentalList(rentallist);
					clear();
				} else if(!rentOK){
					JOptionPane.showMessageDialog(this, "책 번호 중복 다시");
				}
			} catch(NumberFormatException e2){
				JOptionPane.showMessageDialog(this,"숫자를 입력하세요!!");
				return;
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("예외");
				e1.printStackTrace();
			}
		} else if (command.equals("반납")) {
			try {
				String bookNo = bookNoTF.getText().trim();
				Rental rental = rentalManager.findByNo(Integer.parseInt(bookNo));
				boolean rentalOK = rentalManager.returnBook(rental);
				if (rentalOK) {
					JOptionPane.showMessageDialog(this, "반환성공");
					ArrayList<Rental> rentallist = rentalManager.listOfRental();
					ArrayList<Book> booklist = bookManager.listOfBook();
					displayRentalList(rentallist);
					displayBookList(booklist);
					clear();
				} else {
					JOptionPane.showMessageDialog(this, "반환실패");
				}
			}catch(NumberFormatException e2){
				JOptionPane.showMessageDialog(this,"숫자를 입력하세요!!");
				return;
			}catch(Exception e1){
				System.out.println("예외");
			}
		} else if (command.equals("뒤로")) {
			dispose();
			new LibraryMainFrame();
		}
	}
	
	

	private void displayBook(Book book) {
		bookList.clear();
		bookList.add("====================================================================\n");
		bookList.add("  책번호     책이름                          출판사                 저자                    구분                 대출현황\n");
		bookList.add("====================================================================\n");
		bookList.add(book.toString() + "\n");
	}

	private void displayBookList(ArrayList<Book> booklist) {
		bookList.clear();
		bookList.add("====================================================================\n");
		bookList.add("  책번호     책이름                          출판사                 저자                    구분                 대출현황\n");
		bookList.add("====================================================================\n");
		for (int i = 0; i < booklist.size(); i++) {
			bookList.add(booklist.get(i) + "\n");
		}
	}
	
	private void displayRental(Rental rental) {
		rentalList.clear();
		rentalList.add(rental.toString() + "\n");
	}

	private void displayRentalList(ArrayList<Rental> rentallist)throws Exception {
		int count=1;
		rentalList.clear();
		for (int i = 0; i < rentallist.size(); i++) {
			Book book = bookManager.findByNo(rentallist.get(i).getRentalBookNo());
			Member member = memberManager.findByNo(rentallist.get(i).getRentalMemberNo());
			//rentalList.add(rentallist.get(i)+"\n");
			rentalList.add(count+" : \t 책 번호 : "+book.getBookNo()+"   "+" : \t 책 이름 : "+book.getBookName()+"   "+"\t 회원 이름 : "+member.getMemberName()+"\n");
			count++;
		}
	}
	


	/********** WindowEvent ********/
	public class WindowHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			dispose();
			new LibraryMainFrame();
		}
	}

	public static void main(String[] args) throws Exception {
		new RentalUI();
	}
}
