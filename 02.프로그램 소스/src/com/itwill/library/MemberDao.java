package com.itwill.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
 * Dao(Data Access Object) - Manager에서 회원의 파일정보에(Data) 접근하기위해서 사용하는객체 - 멤버데이타를
 * 저장하고 있는 파일(Data) 에 쓰기,읽기,수정,삭제를 하는객체 - RandomAccessFile을 이용한 파일 영속성 처리
 */
public class MemberDao {
	public MemberDao() throws Exception {
		File file = new File("member.ser");
		if (!file.exists()) {
			file.createNewFile();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(new ArrayList<Member>());// 최초 한번만 실행 => 초기화 작업
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
																		// 데이터를
																		// 쓰는
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("member.ser"));
		oos.writeObject(memberlist);
		oos.close();
	}

	// create
	public void add(Member member) throws Exception {// 저장
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
				this.write(memberList);// 얘 필수..!!!! 다시써야대
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
				this.write(memberList);// 전체로 들어간다.
				break;
			}
		}
		return member2;
	}

}
