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

public class BookUI extends Frame implements ActionListener {

	public static final int NONE = 0;
	public static final int ADD = 1; // 추가
	public static final int DELETE = 2; // 삭제
	public static final int FIND = 3; // 검색
	public static final int CANCEL = 4; // 취소
	public static final int UPDATE = 5; // 수정
	public static final int BACK = 6; // 뒤로

	BookManager bookManager;

	Panel northP, centerP, southP; // 구성패널
	Label noL, nameL, writerL, companyL, typeL, isRentL; // 상단라벨
	TextField noTF, nameTF, writerTF, companyTF, typeTF, isRentTF; // 상단텍스트필드
	Button addB, updateB, deleteB, findB, cancelB, backB; // 하단버튼
	TextArea centerTA;
	List list;

	int cmd;

	ArrayList<Book> booklist;

	public BookUI() {

		setTitle("도서 관리");

		////////////////////////////// 상단패널
		northP = new Panel();

		noL = new Label("도서번호");
		nameL = new Label("도서이름");
		writerL = new Label("저자");
		companyL = new Label("출판사");
		typeL = new Label("장르");
		isRentL = new Label("대출가능");

		list = new List(30);

		noTF = new TextField(100);
		nameTF = new TextField(100);
		writerTF = new TextField(100);
		companyTF = new TextField(100);
		typeTF = new TextField(100);
		isRentTF = new TextField(100);

		northP.setLayout(new GridLayout(3, 6));
		northP.add(noL);
		northP.add(noTF);
		northP.add(nameL);
		northP.add(nameTF);
		northP.add(writerL);
		northP.add(writerTF);
		northP.add(companyL);
		northP.add(companyTF);
		northP.add(typeL);
		northP.add(typeTF);
		northP.add(isRentL);
		northP.add(isRentTF);

		add("North", northP);

		///////////////////////////// 중간패널
		centerP = new Panel();
		centerTA = new TextArea();

		centerP.setLayout(new BorderLayout());
		centerP.add(list);

		add(centerP, BorderLayout.CENTER);

		/////////////////////////// 하단패널
		southP = new Panel();

		addB = new Button("추가");
		updateB = new Button("수정");
		deleteB = new Button("삭제");
		findB = new Button("찾기");
		cancelB = new Button("취소");
		backB = new Button("뒤로");

		southP.setLayout(new GridLayout(1, 6));
		southP.add(addB);
		southP.add(updateB);
		southP.add(deleteB);
		southP.add(findB);
		southP.add(cancelB);
		southP.add(backB);

		add("South", southP);

		// event
		this.addWindowListener(new WindowHandler());

		addB.addActionListener(this);
		updateB.addActionListener(this);
		deleteB.addActionListener(this);
		findB.addActionListener(this);
		cancelB.addActionListener(this);
		backB.addActionListener(this);

		list.setVisible(true);

		setSize(500, 600);

		// 모니터 중간에 위치하도록
		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);

		updateB.setBackground(Color.pink);
		addB.setBackground(Color.pink);
		deleteB.setBackground(Color.pink);
		findB.setBackground(Color.pink);
		cancelB.setBackground(Color.pink);
		backB.setBackground(Color.pink);

		try {
			bookManager = new BookManager();
			booklist = bookManager.listOfBook();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		initialize(); // TextField 초기화
		displayBookList(booklist);
		setVisible(true);
	}

	// TextField 초기화
	// 수정불가능
	public void initialize() {
		noTF.setEditable(false);
		nameTF.setEditable(false);
		writerTF.setEditable(false);
		companyTF.setEditable(false);
		typeTF.setEditable(false);
		isRentTF.setEditable(false);
	}

	// TextField noTF, nameTF, writerTF, companyTF, typeTF, isRentTF; // 상단텍스트필드
	// TextField 활성화 관련 메소드
	public void setEditable(int n) {
		switch (n) {
		case ADD: // 추가: 번호, 이름, 저자, 출판사, 장르만 열림
			noTF.setEditable(true);
			nameTF.setEditable(true);
			writerTF.setEditable(true);
			companyTF.setEditable(true);
			typeTF.setEditable(true);
			break;
		case NONE:
		case DELETE: // 삭제: 번호만 열림
			noTF.setEditable(true);
			break;
		case FIND: // 찾기: 번호만 열림
			noTF.setEditable(true);
			// nameTF.setEditable(true);
			break;
		case CANCEL: // 취소: TextField 비움
			clear();
			break;
		case UPDATE: // 수정: 번호, 이름, 저자, 출판사, 장르만 열림
			noTF.setEditable(true);
			nameTF.setEditable(true);
			writerTF.setEditable(true);
			companyTF.setEditable(true);
			typeTF.setEditable(true);
			break;
		}
	}

	// Button addB, updateB, deleteB, findB, cancelB, backB; // 하단버튼
	// 버튼 활성화 관련 메소드
	public void setEnable(int n) {
		addB.setEnabled(false); // 비활성화
		deleteB.setEnabled(false);
		findB.setEnabled(false);
		updateB.setEnabled(false);
		backB.setEnabled(false);

		switch (n) {
		case ADD: // 추가 버튼 누르면 추가 and 취소 버튼만 활성화
			addB.setEnabled(true);
			setEditable(ADD);
			cmd = ADD;
			break;
		case DELETE: // 삭제 버튼 누르면 삭제 and 취소 버튼만 활성화
			deleteB.setEnabled(true);
			setEditable(DELETE);
			cmd = DELETE;
			break;
		case FIND: // 찾기 버튼 누르면 검색 and 취소 버튼만 활성화
			findB.setEnabled(true);
			setEditable(FIND);
			cmd = FIND;
			break;
		case UPDATE: // 수정 버튼 누르면 수정 and 취소 버튼만 활성화
			updateB.setEnabled(true);
			setEditable(UPDATE);
			cmd = UPDATE;
			break;
		case BACK: // 뒤로 버튼 누르면 뒤로 버튼만 활성화
			backB.setEnabled(true);
			cancelB.setEnabled(false);
			setEditable(BACK);
			cmd = BACK;
			break;
		case NONE: // 취소 버튼 누르면 모두 활성화
			addB.setEnabled(true);
			deleteB.setEnabled(true);
			findB.setEnabled(true);
			updateB.setEnabled(true);
			backB.setEnabled(true);
		}
	}

	public void clear() {
		noTF.setText("");
		nameTF.setText("");
		writerTF.setText("");
		companyTF.setText("");
		typeTF.setText("");
	}

	// 버튼.setEnable(false) : 버튼 비활성화
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("추가")) {
			try {
				if (cmd != ADD) {
					setEnable(ADD);
				} else {
					String vNo = noTF.getText().trim();
					String vName = nameTF.getText().trim();
					String vWriter = writerTF.getText().trim();
					String vCompany = companyTF.getText().trim();
					String vType = typeTF.getText().trim();

					Book addBook = new Book(Integer.parseInt(vNo), vName, vWriter, vCompany, vType, false);

					boolean bookOK;
					bookOK = bookManager.addBook(addBook);
					if (bookOK) {
						JOptionPane.showMessageDialog(this, "등록완료!!");
						ArrayList<Book> bookList = bookManager.listOfBook();
						displayBookList(bookList);
					} else {
						JOptionPane.showMessageDialog(this, "등록실패(번호중복)!!");
					}
					setEnable(NONE);
					clear();
					cmd = NONE;
					initialize();
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "입력해주세요");
			}
		} else if (command.equals("수정")) {
			try {
				if (cmd != UPDATE) {
					setEnable(UPDATE);
				} else {
					String vNo = noTF.getText().trim();
					String vName = nameTF.getText().trim();
					String vWriter = writerTF.getText().trim();
					String vCompany = companyTF.getText().trim();
					String vType = typeTF.getText().trim();

					Book updateBook = new Book(Integer.parseInt(vNo), vName, vWriter, vCompany, vType, false);

					if (vNo == null || vName == null || vWriter == null || vCompany == null || vType == null
							|| vNo.length() == 0 || vName.length() == 0 || vWriter.length() == 0
							|| vCompany.length() == 0) {
						JOptionPane.showMessageDialog(this, "입력하세요");
						noTF.requestFocus();
						return;
					}

					boolean bookOK;
					bookOK = bookManager.updateBook(updateBook);
					if (!bookOK) {
						JOptionPane.showMessageDialog(this, "수정 성공");
						ArrayList<Book> bookList = bookManager.listOfBook();
						displayBookList(bookList);
					} else {
						JOptionPane.showMessageDialog(this, "수정 실패");
					}
					if (vName == null || vName.length() == 0) {
						return;
					}
					setEnable(NONE);
					clear();
					cmd = NONE;
					initialize();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "입력해주세요");
			}
		} else if (command.equals("삭제")) {
			try {
				if (cmd != DELETE) {
					setEnable(DELETE);
				} else {
					String vnum = noTF.getText().trim();

					int no = Integer.parseInt(vnum);
					boolean delete = bookManager.deleteBook(no);
					if (!delete) {
						JOptionPane.showMessageDialog(this, "삭제성공");
						ArrayList<Book> booklist;
						booklist = bookManager.listOfBook();
						displayBookList(booklist);
					} else {
						JOptionPane.showMessageDialog(this, "삭제 실패!!(책 없음)");
					}
					if (vnum == null || vnum.length() == 0)
						return;
					setEnable(NONE);
					clear();
					cmd = NONE;
					initialize();
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "입력해주세요");
			}
		} else if (command.equals("찾기")) {
			try {
				if (cmd != FIND) {
					setEnable(FIND);
				} else {
					String vnum = noTF.getText().trim();
					int no = Integer.parseInt(vnum);
					Book search = bookManager.findByNo(no);
					if (search != null) {
						JOptionPane.showMessageDialog(this, "찾기성공");
						Book book;
						book = bookManager.findByNo(no);
						displayBook(book);
					} else {
						JOptionPane.showMessageDialog(this, "찾기 실패!!(책 없음)");
					}
					if (vnum == null || vnum.length() == 0)
						return;
					setEnable(NONE);
					clear();
					cmd = NONE;
					initialize();
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "입력해주세요");
			}
		} else if (command.equals("뒤로")) {
			// 현재 프레임 끄기.
			dispose();
			// 원래의 프레임 생성.
			new LibraryMainFrame();
		} else if (command.equals("취소")) {
			setEnable(NONE);
			String vNo = noTF.getText().trim();
			String vName = nameTF.getText().trim();
			String vWriter = writerTF.getText().trim();
			String vCommpany = companyTF.getText().trim();
			String vType = typeTF.getText().trim();
			if (vNo != null || vName != null || vWriter != null || vCommpany != null || vType != null)
				setEnable(NONE);
			initialize();
			cmd = NONE;
		}
	}

	private void displayBook(Book book) {
		list.clear();
		headerPrint();
		list.add(book.toString() + "\n");
	}

	private void displayBookList(ArrayList<Book> booklist) {
		list.clear();
		headerPrint();
		for (Book book : booklist) {
			list.add(book.toString());
		}
	}

	private void headerPrint() {
		list.clear();
		list.add("====================================================================\n");
		list.add("책번호     책이름                          출판사                 저자                    구분                 대출현황\n");
		list.add("====================================================================\n");
	}

	/********** WindowEvent ********/
	public class WindowHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new BookUI();
	}
}
