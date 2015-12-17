package com.itwill.library;

import java.util.ArrayList;

/*
 - ȸ�������� ���� ����Ͻ� �޽�带 �����ϴ� Ŭ����
 - UIŬ�������� ȸ�������� ���� ����ϴ� Ŭ����
 - ����Ͻ��޽�� ������ Data Accessing �� �ʿ��ϸ� Dao ��ü�� �޽�带���

 */
public class MemberManager {

	MemberDao memberDao;

	public MemberManager() throws Exception {
		memberDao = new MemberDao();
	}

	/*
	 * ȸ������
	 */
	public boolean addMember(Member member) throws Exception {
		boolean joinOK = false;
		// 1.���̵���ȿ��üũ
		Member getMember = memberDao.readByNo(member.getMemberNo());
		// 2.����
		if (getMember == null) {
			memberDao.add(member);
			joinOK = true;
		} else {
		}
		return joinOK;
	}

	/*
	 * ȸ����ȣ��ȸ
	 */
	public Member findByNo(int no) throws Exception {
		Member member = memberDao.readByNo(no);
		return member;
	}

	/*
	 * ȸ���̸���ȸ
	 */
	public Member findByName(String name) throws Exception {
		Member member = memberDao.readByName(name);
		return member;
	}

	/*
	 * ȸ����ü��ȸ
	 */
	public ArrayList<Member> listOfMember() throws Exception {
		ArrayList<Member> memberlist = memberDao.readAll();
		return memberlist;
	}

	/*
	 * ȸ������
	 */
	public boolean updateMember(Member member) throws Exception {
		boolean update = false;
		Member member2 = memberDao.update(member);
		if (member2 == null) {
			memberDao.update(member);
			update = true;
		} else {
		}
		return update;
	}

	/*
	 * ȸ������
	 */
	public boolean deleteMember(int memberNo) throws Exception {
		Member getMember = memberDao.delete(memberNo);
		// 2.����
		boolean delete = false;
		if (getMember == null) {
			memberDao.delete(memberNo);
			delete = true;
		} else {
		}
		return delete;
	}
}
