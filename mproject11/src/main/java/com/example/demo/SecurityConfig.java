package com.example.demo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.password.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("spring").password(passwordEncoder.encode("1234")).roles("USER");
		auth.inMemoryAuthentication().withUser("summer").password(passwordEncoder.encode("1234")).roles("USER");
		auth.inMemoryAuthentication().withUser("winter").password(passwordEncoder.encode("1234")).roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// csrf: html 위조를 방어하는 기술. 일단 꺼놓고 작업하자
		http.csrf().disable();
		// mvc 로그인 활성화(자동설정)
		http.formLogin();
		http.authorizeHttpRequests().antMatchers("/v3/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll();
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
	}
}





