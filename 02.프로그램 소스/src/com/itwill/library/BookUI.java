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
	public static final int ADD = 1; // �߰�
	public static final int DELETE = 2; // ����
	public static final int FIND = 3; // �˻�
	public static final int CANCEL = 4; // ���
	public static final int UPDATE = 5; // ����
	public static final int BACK = 6; // �ڷ�

	BookManager bookManager;

	Panel northP, centerP, southP; // �����г�
	Label noL, nameL, writerL, companyL, typeL, isRentL; // ��ܶ�
	TextField noTF, nameTF, writerTF, companyTF, typeTF, isRentTF; // ����ؽ�Ʈ�ʵ�
	Button addB, updateB, deleteB, findB, cancelB, backB; // �ϴܹ�ư
	TextArea centerTA;
	List list;

	int cmd;

	ArrayList<Book> booklist;

	public BookUI() {

		setTitle("���� ����");

		////////////////////////////// ����г�
		northP = new Panel();

		noL = new Label("������ȣ");
		nameL = new Label("�����̸�");
		writerL = new Label("����");
		companyL = new Label("���ǻ�");
		typeL = new Label("�帣");
		isRentL = new Label("���Ⱑ��");

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

		///////////////////////////// �߰��г�
		centerP = new Panel();
		centerTA = new TextArea();

		centerP.setLayout(new BorderLayout());
		centerP.add(list);

		add(centerP, BorderLayout.CENTER);

		/////////////////////////// �ϴ��г�
		southP = new Panel();

		addB = new Button("�߰�");
		updateB = new Button("����");
		deleteB = new Button("����");
		findB = new Button("ã��");
		cancelB = new Button("���");
		backB = new Button("�ڷ�");

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

		// ����� �߰��� ��ġ�ϵ���
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

		initialize(); // TextField �ʱ�ȭ
		displayBookList(booklist);
		setVisible(true);
	}

	// TextField �ʱ�ȭ
	// �����Ұ���
	public void initialize() {
		noTF.setEditable(false);
		nameTF.setEditable(false);
		writerTF.setEditable(false);
		companyTF.setEditable(false);
		typeTF.setEditable(false);
		isRentTF.setEditable(false);
	}

	// TextField noTF, nameTF, writerTF, companyTF, typeTF, isRentTF; // ����ؽ�Ʈ�ʵ�
	// TextField Ȱ��ȭ ���� �޼ҵ�
	public void setEditable(int n) {
		switch (n) {
		case ADD: // �߰�: ��ȣ, �̸�, ����, ���ǻ�, �帣�� ����
			noTF.setEditable(true);
			nameTF.setEditable(true);
			writerTF.setEditable(true);
			companyTF.setEditable(true);
			typeTF.setEditable(true);
			break;
		case NONE:
		case DELETE: // ����: ��ȣ�� ����
			noTF.setEditable(true);
			break;
		case FIND: // ã��: ��ȣ�� ����
			noTF.setEditable(true);
			// nameTF.setEditable(true);
			break;
		case CANCEL: // ���: TextField ���
			clear();
			break;
		case UPDATE: // ����: ��ȣ, �̸�, ����, ���ǻ�, �帣�� ����
			noTF.setEditable(true);
			nameTF.setEditable(true);
			writerTF.setEditable(true);
			companyTF.setEditable(true);
			typeTF.setEditable(true);
			break;
		}
	}

	// Button addB, updateB, deleteB, findB, cancelB, backB; // �ϴܹ�ư
	// ��ư Ȱ��ȭ ���� �޼ҵ�
	public void setEnable(int n) {
		addB.setEnabled(false); // ��Ȱ��ȭ
		deleteB.setEnabled(false);
		findB.setEnabled(false);
		updateB.setEnabled(false);
		backB.setEnabled(false);

		switch (n) {
		case ADD: // �߰� ��ư ������ �߰� and ��� ��ư�� Ȱ��ȭ
			addB.setEnabled(true);
			setEditable(ADD);
			cmd = ADD;
			break;
		case DELETE: // ���� ��ư ������ ���� and ��� ��ư�� Ȱ��ȭ
			deleteB.setEnabled(true);
			setEditable(DELETE);
			cmd = DELETE;
			break;
		case FIND: // ã�� ��ư ������ �˻� and ��� ��ư�� Ȱ��ȭ
			findB.setEnabled(true);
			setEditable(FIND);
			cmd = FIND;
			break;
		case UPDATE: // ���� ��ư ������ ���� and ��� ��ư�� Ȱ��ȭ
			updateB.setEnabled(true);
			setEditable(UPDATE);
			cmd = UPDATE;
			break;
		case BACK: // �ڷ� ��ư ������ �ڷ� ��ư�� Ȱ��ȭ
			backB.setEnabled(true);
			cancelB.setEnabled(false);
			setEditable(BACK);
			cmd = BACK;
			break;
		case NONE: // ��� ��ư ������ ��� Ȱ��ȭ
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

	// ��ư.setEnable(false) : ��ư ��Ȱ��ȭ
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("�߰�")) {
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
						JOptionPane.showMessageDialog(this, "��ϿϷ�!!");
						ArrayList<Book> bookList = bookManager.listOfBook();
						displayBookList(bookList);
					} else {
						JOptionPane.showMessageDialog(this, "��Ͻ���(��ȣ�ߺ�)!!");
					}
					setEnable(NONE);
					clear();
					cmd = NONE;
					initialize();
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "�Է����ּ���");
			}
		} else if (command.equals("����")) {
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
						JOptionPane.showMessageDialog(this, "�Է��ϼ���");
						noTF.requestFocus();
						return;
					}

					boolean bookOK;
					bookOK = bookManager.updateBook(updateBook);
					if (!bookOK) {
						JOptionPane.showMessageDialog(this, "���� ����");
						ArrayList<Book> bookList = bookManager.listOfBook();
						displayBookList(bookList);
					} else {
						JOptionPane.showMessageDialog(this, "���� ����");
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
				JOptionPane.showMessageDialog(this, "�Է����ּ���");
			}
		} else if (command.equals("����")) {
			try {
				if (cmd != DELETE) {
					setEnable(DELETE);
				} else {
					String vnum = noTF.getText().trim();

					int no = Integer.parseInt(vnum);
					boolean delete = bookManager.deleteBook(no);
					if (!delete) {
						JOptionPane.showMessageDialog(this, "��������");
						ArrayList<Book> booklist;
						booklist = bookManager.listOfBook();
						displayBookList(booklist);
					} else {
						JOptionPane.showMessageDialog(this, "���� ����!!(å ����)");
					}
					if (vnum == null || vnum.length() == 0)
						return;
					setEnable(NONE);
					clear();
					cmd = NONE;
					initialize();
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "�Է����ּ���");
			}
		} else if (command.equals("ã��")) {
			try {
				if (cmd != FIND) {
					setEnable(FIND);
				} else {
					String vnum = noTF.getText().trim();
					int no = Integer.parseInt(vnum);
					Book search = bookManager.findByNo(no);
					if (search != null) {
						JOptionPane.showMessageDialog(this, "ã�⼺��");
						Book book;
						book = bookManager.findByNo(no);
						displayBook(book);
					} else {
						JOptionPane.showMessageDialog(this, "ã�� ����!!(å ����)");
					}
					if (vnum == null || vnum.length() == 0)
						return;
					setEnable(NONE);
					clear();
					cmd = NONE;
					initialize();
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "�Է����ּ���");
			}
		} else if (command.equals("�ڷ�")) {
			// ���� ������ ����.
			dispose();
			// ������ ������ ����.
			new LibraryMainFrame();
		} else if (command.equals("���")) {
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
		list.add("å��ȣ     å�̸�                          ���ǻ�                 ����                    ����                 ������Ȳ\n");
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
