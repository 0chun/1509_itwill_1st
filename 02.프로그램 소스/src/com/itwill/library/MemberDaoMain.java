package com.itwill.library;

import java.util.ArrayList;

public class MemberDaoMain {
	public static void main(String[] args) throws Exception {
		MemberDao memberDao = new MemberDao();
/*		memberDao.add(new Member(1, "KIM", "º≠øÔ", "018-331-315"));
		memberDao.add(new Member(2, "GIM", "¥Î¿¸", "011-532-255"));
		memberDao.add(new Member(3, "DIM", "¥Î±∏", "011-231-575"));
		memberDao.add(new Member(5, "CIM", "∫ŒªÍ", "016-123-345"));
		memberDao.add(new Member(6, "VIM", "¿Œ√µ", "017-678-999"));
		memberDao.add(new Member(7, "BIM", "¡¶¡÷", "017-544-444"));
		memberDao.add(new Member(8, "RIM", "∞≠ø¯", "010-531-358"));
		memberDao.add(new Member(9, "TIM", "±§¡÷", "017-731-358"));*/
		MemberDao dao = new MemberDao();
		//memberDao.update(new Member(3, "ΩÆΩÆΩÆ", "213", "132"));
		//memberDao.delete(3);
		ArrayList<Member> memberList = memberDao.readAll();
		//(new Member(3, "ΩÆΩÆΩÆ", "213", "132"));
		
		for (int i = 0; i < memberList.size(); i++) {
			System.out.println(memberList.get(i));
		}
		
	}
}
