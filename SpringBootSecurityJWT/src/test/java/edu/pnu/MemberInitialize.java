package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

//JUNIT으로 테스트해서 DB에 입력된 데이터 확인
@SpringBootTest
public class MemberInitialize {

	@Autowired
	MemberRepository memRepo; //Table Member Crud 인터페이스
	
	PasswordEncoder encoder = new BCryptPasswordEncoder(); //비밀번호 암호화 인터페이스/구현체
	
	@Test
	public void dowork() {
		memRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("asdf"))
				.role("ROLE_MEMBER")
				.enabled(true).build());

		memRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("asdf"))
				.role("ROLE_MANAGER")
				.enabled(true).build());
		
		memRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("asdf"))
				.role("ROLE_ADMIN")
				.enabled(true).build());
	}
	
}
