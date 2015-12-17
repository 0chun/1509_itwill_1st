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
	Panel buttonP, listP, NorthP; // ��ư ���
	
	Label bookNoL, memberNoL;
	TextField bookNoTF, memberNoTF;
	
	TextArea bookTA, rentalTA;
	
	ArrayList<Book> booklist;
	ArrayList<Rental> rentallist;
	

	public RentalUI() throws Exception {

		setTitle("�뿩/�ݳ�����");

		bookNoL = new Label("å��ȣ");
		memberNoL = new Label("ȸ����ȣ");
		
		bookNoTF = new TextField(5);
		memberNoTF = new TextField(5);
		
		// �ϴ� ��ư
		rentalB = new Button("�뿩");
		returnB = new Button("�ݳ�");
		backB = new Button("�ڷ�");
		// ��ư ���
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
		
		// ����Ʈ �г�
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
		if (command.equals("�뿩")) {
			try {
				String vBook = bookNoTF.getText().trim();
				String vMember = memberNoTF.getText().trim();
				
				Rental addRental = new Rental(Integer.parseInt(vBook),Integer.parseInt(vMember));
				boolean rentOK;
				rentOK = rentalManager.rentBook(addRental);
				if (rentOK) {
					JOptionPane.showMessageDialog(this, "���⼺��!!!");
					ArrayList<Rental> rentallist = rentalManager.listOfRental();
					ArrayList<Book> booklist = bookManager.listOfBook();
					displayBookList(booklist);
					displayRentalList(rentallist);
					clear();
				} else if(!rentOK){
					JOptionPane.showMessageDialog(this, "å ��ȣ �ߺ� �ٽ�");
				}
			} catch(NumberFormatException e2){
				JOptionPane.showMessageDialog(this,"���ڸ� �Է��ϼ���!!");
				return;
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("����");
				e1.printStackTrace();
			}
		} else if (command.equals("�ݳ�")) {
			try {
				String bookNo = bookNoTF.getText().trim();
				Rental rental = rentalManager.findByNo(Integer.parseInt(bookNo));
				boolean rentalOK = rentalManager.returnBook(rental);
				if (rentalOK) {
					JOptionPane.showMessageDialog(this, "��ȯ����");
					ArrayList<Rental> rentallist = rentalManager.listOfRental();
					ArrayList<Book> booklist = bookManager.listOfBook();
					displayRentalList(rentallist);
					displayBookList(booklist);
					clear();
				} else {
					JOptionPane.showMessageDialog(this, "��ȯ����");
				}
			}catch(NumberFormatException e2){
				JOptionPane.showMessageDialog(this,"���ڸ� �Է��ϼ���!!");
				return;
			}catch(Exception e1){
				System.out.println("����");
			}
		} else if (command.equals("�ڷ�")) {
			dispose();
			new LibraryMainFrame();
		}
	}
	
	

	private void displayBook(Book book) {
		bookList.clear();
		bookList.add("====================================================================\n");
		bookList.add("  å��ȣ     å�̸�                          ���ǻ�                 ����                    ����                 ������Ȳ\n");
		bookList.add("====================================================================\n");
		bookList.add(book.toString() + "\n");
	}

	private void displayBookList(ArrayList<Book> booklist) {
		bookList.clear();
		bookList.add("====================================================================\n");
		bookList.add("  å��ȣ     å�̸�                          ���ǻ�                 ����                    ����                 ������Ȳ\n");
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
			rentalList.add(count+" : \t å ��ȣ : "+book.getBookNo()+"   "+" : \t å �̸� : "+book.getBookName()+"   "+"\t ȸ�� �̸� : "+member.getMemberName()+"\n");
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
