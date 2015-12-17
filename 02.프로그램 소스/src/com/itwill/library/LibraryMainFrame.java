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

	Button memberB, bookB, rentalB; // ��� ��ư(ȸ������, ��������, �뿩�ݳ�����)
	Panel buttonP; // ��ư ���
	BookUI bookP; // �������� ���
	MemberUI memberP; // ȸ������ ���
	RentalUI rentalP; // �뿩�ݳ����� ���

	public LibraryMainFrame() {

		setTitle("Library Management Program");

		// ��� ��ư
		bookB = new Button("��������");
		memberB = new Button("ȸ������");
		rentalB = new Button("�뿩/�ݳ�");
		// ��ư ���
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
			if (cmd.equals("ȸ������")) { // ȸ������ â���� �̵�
				dispose();
				new MemberUI();
			} else if (cmd.equals("��������")) { // �������� â���� �̵�
				dispose();
				new BookUI();
			} else if (cmd.equals("�뿩/�ݳ�")) { // �뿩/�ݳ� ���� â���� �̵�
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
