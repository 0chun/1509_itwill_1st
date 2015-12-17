package com.itwill.library;

import java.io.Serializable;

/*
DTO(Data Transfer Object),VO(Value Object)
   - 멤버 1명의 데이타를 가지고있는객체
   - Member의 회원번호는 회원을 구분할수있는 유일한값이어야한다.
*/
public class Member implements Serializable {
	private int memberNo;
	private String memberName;
	private String memberAdd;// 주소
	private String memberTel;

	public Member() {

	}

	public Member(int memberNo, String memberName, String memberAdd, String memberTel) {
		super();
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.memberAdd = memberAdd;
		this.memberTel = memberTel;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAdd() {
		return memberAdd;
	}

	public void setMemberAdd(String memberAdd) {
		this.memberAdd = memberAdd;
	}

	public String getMemberTel() {
		return memberTel;
	}

	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String MemberName  = "              ".substring(memberName.length()) + memberName;
		String MemberTel = "              ".substring(memberTel.length()) + memberTel;
		String MemberAdd    = "              ".substring(memberAdd.length()) + memberAdd;
		return String.format("%d  %20s %20s %20s",memberNo,MemberName,MemberTel,MemberAdd).toString();
	}
	
}