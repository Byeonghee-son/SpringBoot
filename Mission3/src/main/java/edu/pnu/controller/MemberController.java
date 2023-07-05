package edu.pnu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;



@RestController
public class MemberController {
	private MemberService memberService;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	public MemberController() {
		memberService = new MemberService();
		
	}
	
	@GetMapping("/members")
	public List<MemberVO> getMembers(){
		log.info("getMembers()");
		System.out.println("getMembers");
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMember1(@RequestBody MemberVO member) {
		log.info("getMember()");
		return memberService.getMember(member.getId());
	}
	
	@PostMapping("/member")
	public int addMember(MemberVO member) {
		log.info("addMember()");
		System.out.println(member);
		return memberService.addMember(member);
	}

	@PutMapping("/member")
	public int updateMember(MemberVO member) {
		log.info("updateMember()");
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public int deleteMember(@PathVariable Integer id) {
		log.info("deleteMember()");
		return memberService.deleteMember(id);
	}
}

