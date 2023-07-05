package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;


//service니까 어노테이션 붙여줘야함 안그럼 로그인성공이 안됨
@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memRepo;
	
	@Override  //AuthenticationManager의 authenticate 메소드가 호출되면 실행
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memRepo.findById(username).orElseThrow(()->
				new UsernameNotFoundException("Not Found!"));
		return new User(member.getUsername(),member.getPassword(),member.getAuthorities());
	}

}
