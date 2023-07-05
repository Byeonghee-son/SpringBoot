package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDaoH2Imp;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {

	private MemberInterface memberDao;
	
	public MemberService() {
		memberDao = new MemberDaoH2Imp();
		//memberDao = new MemberDaoListImpl();
	}
	
	public List<MemberVO> getMembers() {
		return memberDao.getMembers();
	}

	public MemberVO getMember(Integer id) {
		return memberDao.getMember(id);
	}

	public int addMember(MemberVO member) {
		return memberDao.addMember(member);
	}

	public int updateMember(MemberVO member) {
		return memberDao.updateMember(member);
	}

	public int deleteMember(Integer id) {
		return memberDao.deleteMember(id);
	}
}
