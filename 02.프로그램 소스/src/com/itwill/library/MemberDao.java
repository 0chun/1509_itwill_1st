package com.itwill.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
 * Dao(Data Access Object) - Manager���� ȸ���� ����������(Data) �����ϱ����ؼ� ����ϴ°�ü - �������Ÿ��
 * �����ϰ� �ִ� ����(Data) �� ����,�б�,����,������ �ϴ°�ü - RandomAccessFile�� �̿��� ���� ���Ӽ� ó��
 */
public class MemberDao {
	public MemberDao() throws Exception {
		File file = new File("member.ser");
		if (!file.exists()) {
			file.createNewFile();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(new ArrayList<Member>());// ���� �ѹ��� ���� => �ʱ�ȭ �۾�
			oos.close();
		}
	}

	// open
	private ArrayList<Member> open() throws Exception {

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("member.ser"));
		ArrayList<Member> memberlist = (ArrayList<Member>) ois.readObject();
		ois.close();
		return memberlist;
	}

	// close
	private void close(ObjectInputStream ois) throws Exception {
		ois.close();
	}

	// write
	private void write(ArrayList<Member> memberlist) throws Exception {// object
																		// �����͸�
																		// ����
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("member.ser"));
		oos.writeObject(memberlist);
		oos.close();
	}

	// create
	public void add(Member member) throws Exception {// ����
		ArrayList<Member> memberlist = this.open();
		memberlist.add(member);
		this.write(memberlist);
	}

	// read
	public Member readByNo(int no) throws Exception {
		Member member = null;
		ArrayList<Member> memberlist = open();
		for (int i = 0; i < memberlist.size(); i++) {
			Member tempMember = memberlist.get(i);
			if (tempMember.getMemberNo() == no) {
				member = tempMember;
				break;
			}
		}
		return member;
	}

	// read
	public Member readByName(String name) throws Exception {
		Member member = null;
		ArrayList<Member> memberlist = open();
		for (int i = 0; i < memberlist.size(); i++) {
			Member tempMember = memberlist.get(i);
			if (tempMember.getMemberName().equals(name)) {
				member = tempMember;
				break;
			}
		}
		return member;
	}

	// read
	public ArrayList<Member> readAll() throws Exception {
		ArrayList<Member> memberList = open();
		return memberList;

	}

	// update
	public Member update(Member member) throws Exception {
		Member member2 = null;
		ArrayList<Member> memberList = open();

		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getMemberNo() == member.getMemberNo()) {
				member2 = memberList.set(i, member);
				this.write(memberList);// �� �ʼ�..!!!! �ٽý�ߴ�
			}
		}
		return member2;
	}

	// delete
	public Member delete(int no) throws Exception {
		Member member2 = null;
		ArrayList<Member> memberList = open();

		for (int i = 0; i < memberList.size(); i++) {
			Member tempMember = memberList.get(i);
			if (tempMember.getMemberNo() == no) {
				member2 = memberList.remove(i);
				this.write(memberList);// ��ü�� ����.
				break;
			}
		}
		return member2;
	}

}
