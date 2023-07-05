package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	BoardUserDetailsService boardUserDetailsService;
	
	//BCryptPasswordEncoder 인스턴스를 만들어서 IOContainer에 올리는것
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
		
		security.authorizeHttpRequests(auth->{
//			auth.requestMatchers("/").permitAll();
//			auth.requestMatchers("/member/**").authenticated();
		
			auth.requestMatchers("/member/**").authenticated()
			.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll();
		});
		
		security.userDetailsService(boardUserDetailsService);
		
		security.csrf(csrf->csrf.disable());
		
		security.formLogin(form->{
			form.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess",true);
			
		});
		security.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		security.logout(logt->{
			logt.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID") //이런것도 넣어주기
			.logoutSuccessUrl("/login");
		});
		
		
		return security.build();
	}

		@Autowired
		public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
				.withUser("member")
				.password("{noop}asdf")
				.roles("MEMBER");
			auth.inMemoryAuthentication()
				.withUser("manager")
				.password("{noop}asdf")
				.roles("MANAGER");
			auth.inMemoryAuthentication()
				.withUser("admin")
				.password("{noop}asdf")
				.roles("ADMIN");
		}
	
}
