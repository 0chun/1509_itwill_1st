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
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MemberUI extends Frame implements ActionListener {

	public static final int NONE = 0;
	public static final int ADD = 1; // �߰�
	public static final int DELETE = 2; // ����
	public static final int FIND = 3; // �˻�
	public static final int CANCEL = 4; // ���
	public static final int UPDATE = 5; // ����
	public static final int BACK = 6; // �ڷ�

	MemberManager manager;

	Panel northP, centerP, southP; // �����г�
	Label noL, nameL, addL, telL; // ��ܶ�
	TextField noTF, nameTF, addTF, telTF; // ����ؽ�Ʈ�ʵ�
	Button addB, updateB, deleteB, findB, cancelB, backB; // �ϴܹ�ư
	List list;// �߾� ����Ʈ

	int cmd = 0;

	ArrayList<Member> memberlist;

	public MemberUI() {

		setTitle("ȸ�� ����");

		////////////////////////////// ����г�
		northP = new Panel();

		noL = new Label("ȸ����ȣ");
		nameL = new Label("�̸�");
		addL = new Label("�ּ�");
		telL = new Label("��ȭ��ȣ");

		list = new List(30);

		noTF = new TextField(100);
		nameTF = new TextField(100);
		addTF = new TextField(100);
		telTF = new TextField(100);

		northP.setLayout(new GridLayout(2, 6));
		northP.add(noL);
		northP.add(noTF);
		northP.add(nameL);
		northP.add(nameTF);
		northP.add(addL);
		northP.add(addTF);
		northP.add(telL);
		northP.add(telTF);

		add("North", northP);

		///////////////////////////// �߰��г�
		centerP = new Panel();
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
			manager = new MemberManager();
			memberlist = manager.listOfMember();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		initialize(); // TextField �ʱ�ȭ
		displayMemberList(memberlist);
		setVisible(true);
	}

	// TextField �ʱ�ȭ
	// �����Ұ���
	public void initialize() {
		noTF.setEditable(false);
		nameTF.setEditable(false);
		telTF.setEditable(false);
		addTF.setEditable(false);
	}

	// TextField noTF, nameTF, writerTF, companyTF, typeTF, isRentTF; // ����ؽ�Ʈ�ʵ�
	// TextField Ȱ��ȭ ���� �޼ҵ�
	public void setEditable(int n) {
		switch (n) {
		case ADD: // �߰�: ��ȣ, �̸�, ����, ���ǻ�, �帣�� ����
			noTF.setEditable(true);
			nameTF.setEditable(true);
			telTF.setEditable(true);
			addTF.setEditable(true);
			break;
		case NONE:
		case DELETE: // ����: ��ȣ�� ����
			noTF.setEditable(true);
			break;
		case FIND: // ã��: ��ȣ�� ����
			noTF.setEditable(true);
			break;
		case CANCEL: // ���: TextField ���
			clear();
			break;
		case UPDATE: // ����: ��ȣ, �̸�, ����, ���ǻ�, �帣�� ����
			noTF.setEditable(true);
			nameTF.setEditable(true);
			telTF.setEditable(true);
			addTF.setEditable(true);
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
		case FIND: // �˻� ��ư ������ �˻� and ��� ��ư�� Ȱ��ȭ
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
		telTF.setText("");
		addTF.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand(); // �ּ� ���� �������� ��
		if (command.equals("�߰�")) {
			try {
				if (cmd != ADD) {
					setEnable(ADD);
				} else {
					String vNo = noTF.getText().trim();
					String vName = nameTF.getText().trim();
					String vTel = telTF.getText().trim();
					String vAdd = addTF.getText().trim();

					Member addMember = new Member(Integer.parseInt(vNo), vName, vAdd, vTel);

					boolean joinOK;
					joinOK = manager.addMember(addMember);
					if (joinOK) {
						JOptionPane.showMessageDialog(this, "���� ��ī!!!");
						ArrayList<Member> memberlist = manager.listOfMember();
						displayMemberList(memberlist);
					} else {
						JOptionPane.showMessageDialog(this, "���̵��ߺ� �ٽ�");
					}
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
					Member search = manager.findByNo(no);
					if (search != null) {
						JOptionPane.showMessageDialog(this, "ã�⼺��");
						Member member;
						member = manager.findByNo(no);
						displayMember(member);
					} else {
						JOptionPane.showMessageDialog(this, "ã�� ����!!(���̵� ����)");
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
		} else if (command.equals("����")) {
			try {
				if (cmd != DELETE) {
					setEnable(DELETE);
				} else {
					String vnum = noTF.getText().trim();

					int no = Integer.parseInt(vnum);
					boolean delete = manager.deleteMember(no);
					if (!delete) {
						JOptionPane.showMessageDialog(this, "��������");
						ArrayList<Member> memberlist;
						memberlist = manager.listOfMember();
						displayMemberList(memberlist);
					} else {
						JOptionPane.showMessageDialog(this, "���� ����!!(���̵� ����)");
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
		} else if (command.equals("����")) {
			try {
				if (cmd != UPDATE) {
					setEnable(UPDATE);
				} else {
					String vNo = noTF.getText().trim();
					String vName = nameTF.getText().trim();
					String vTel = telTF.getText().trim();
					String vAdd = addTF.getText().trim();
					Member addMember = new Member(Integer.parseInt(vNo), vName, vAdd, vTel);

					if (vNo == null || vName == null || vTel == null || vAdd == null || vNo.length() == 0
							|| vName.length() == 0 || vTel.length() == 0 || vAdd.length() == 0) {
						JOptionPane.showMessageDialog(this, "�Է��ϼ���");
						noTF.requestFocus();
						return;
					}
					
					boolean joinOK;
					joinOK = manager.updateMember(addMember);
					if (!joinOK) {
						JOptionPane.showMessageDialog(this, "���� ����!!!");
						ArrayList<Member> memberlist = manager.listOfMember();
						displayMemberList(memberlist);
					} else {
						JOptionPane.showMessageDialog(this, "���� ����!!(���̵� ����)");
					}
					if (vName == null || vName.length() == 0)
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
			String vNo = noTF.getText().trim();
			String vName = nameTF.getText().trim();
			String vTel = telTF.getText().trim();
			String vAdd = addTF.getText().trim();
			if (vNo != null || vName != null || vTel != null || vAdd != null)
				setEnable(NONE);
			initialize();
			cmd = NONE;
		}
	}

	private void displayMember(Member member) {
		list.clear();
		headerPrint();
		list.add(member.toString() + "\n");
	}

	private void displayMemberList(ArrayList<Member> memberlist) {
		list.clear();
		headerPrint();
		for (Member member : memberlist) {
			list.add(member.toString());
		}
	}

	private void headerPrint() {
		list.clear();
		list.add("====================================================================\n");
		list.add(" ȸ����ȣ     �̸�                       ����ó                                  �ּ�\n");
		list.add("====================================================================\n");
	}

	/********** WindowEvent ********/
	public class WindowHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	public static void main(String[] args) throws Exception {
		new MemberUI();
	}

}