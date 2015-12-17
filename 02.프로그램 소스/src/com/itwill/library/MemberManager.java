package com.itwill.library;

import java.util.ArrayList;

/*
 - 회원관리를 위한 비즈니스 메쏘드를 제공하는 클래스
 - UI클래스에서 회원관리를 위해 사용하는 클래스
 - 비즈니스메쏘드 구현시 Data Accessing 이 필요하면 Dao 객체의 메쏘드를사용

 */
public class MemberManager {

	MemberDao memberDao;

	public MemberManager() throws Exception {
		memberDao = new MemberDao();
	}

	/*
	 * 회원가입
	 */
	public boolean addMember(Member member) throws Exception {
		boolean joinOK = false;
		// 1.아이디유효성체크
		Member getMember = memberDao.readByNo(member.getMemberNo());
		// 2.가입
		if (getMember == null) {
			memberDao.add(member);
			joinOK = true;
		} else {
		}
		return joinOK;
	}

	/*
	 * 회원번호조회
	 */
	public Member findByNo(int no) throws Exception {
		Member member = memberDao.readByNo(no);
		return member;
	}

	/*
	 * 회원이름조회
	 */
	public Member findByName(String name) throws Exception {
		Member member = memberDao.readByName(name);
		return member;
	}

	/*
	 * 회원전체조회
	 */
	public ArrayList<Member> listOfMember() throws Exception {
		ArrayList<Member> memberlist = memberDao.readAll();
		return memberlist;
	}

	/*
	 * 회원수정
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
	 * 회원삭제
	 */
	public boolean deleteMember(int memberNo) throws Exception {
		Member getMember = memberDao.delete(memberNo);
		// 2.삭제
		boolean delete = false;
		if (getMember == null) {
			memberDao.delete(memberNo);
			delete = true;
		} else {
		}
		return delete;
	}
}
