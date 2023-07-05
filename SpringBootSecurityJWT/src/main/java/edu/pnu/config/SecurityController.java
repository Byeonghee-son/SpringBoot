package edu.pnu.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//서버를 실행하고 로그인한 뒤 url들이 제대로 호출되는지 확인
@RestController
public class SecurityController {

	@GetMapping("/") public String index() { return "index";}
	@GetMapping("/member") public String member() { return "member";}
	@GetMapping("/manager") public String manager() {return "manager";}
	@GetMapping("/admin") public String admin() {return "admin";}
	
}
