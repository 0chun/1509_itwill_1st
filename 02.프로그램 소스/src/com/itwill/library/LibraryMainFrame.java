package com.itwill.library;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LibraryMainFrame extends Frame implements ActionListener {

	Button memberB, bookB, rentalB; // 상단 버튼(회원관리, 도서관리, 대여반납관리)
	Panel buttonP; // 버튼 페널
	BookUI bookP; // 도서관리 페널
	MemberUI memberP; // 회원관리 페널
	RentalUI rentalP; // 대여반납관리 페널

	public LibraryMainFrame() {

		setTitle("Library Management Program");

		// 상단 버튼
		bookB = new Button("도서관리");
		memberB = new Button("회원관리");
		rentalB = new Button("대여/반납");
		// 버튼 페널
		buttonP = new Panel();
		buttonP.setLayout(new GridLayout(1, 3));
		buttonP.add(bookB);
		buttonP.add(rentalB);
		buttonP.add(memberB);

		setLayout(new BorderLayout());
		add(buttonP);

		// event
		this.addWindowListener(new WindowHandler());
		bookB.addActionListener(this);
		memberB.addActionListener(this);
		rentalB.addActionListener(this);
		setSize(400, 100);
		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		try {
			if (cmd.equals("회원관리")) { // 회원관리 창으로 이동
				dispose();
				new MemberUI();
			} else if (cmd.equals("도서관리")) { // 도서관리 창으로 이동
				dispose();
				new BookUI();
			} else if (cmd.equals("대여/반납")) { // 대여/반납 관리 창으로 이동
				dispose();
				new RentalUI();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new LibraryMainFrame();
	}

	/********** WindowEvent ********/
	public class WindowHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

}
