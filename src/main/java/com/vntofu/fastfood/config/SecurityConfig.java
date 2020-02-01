package com.vntofu.fastfood.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
//@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true) // provides method level security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	Pbkdf2PasswordEncoder pbkdf2;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("zoey").password(pbkdf2.encode("zoey")).roles("admin");
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/vntofu/customer/**", "/", "/vntofu/ajax/*").permitAll() // Authentication is not required for first time customer
		.and()
		.authorizeRequests().antMatchers("/vntofu/**")
		.authenticated()
		.and().formLogin()
		.loginPage("/vntofu/adminLogin") // If have a custom login page
		.permitAll()
		.defaultSuccessUrl("/vntofu/adminAuth", true)
		.and()
		.logout()
		.logoutSuccessUrl("/vntofu/adminLogin/login?logout")
		.and().exceptionHandling().accessDeniedPage("/vntofu/accessDeniedPage");
		http.csrf().disable();
		http.httpBasic();
	}
	
	public void configure(WebSecurity web) {
		// Have to be inside the resource/static folder
		web.ignoring().antMatchers("/css/*");
		web.ignoring().antMatchers("/images/*");
	}
	
	@Bean
	@Primary
	Pbkdf2PasswordEncoder pbkdf2() {
		Pbkdf2PasswordEncoder pbkdf2 = new Pbkdf2PasswordEncoder();
		
		
		return pbkdf2;
	}

}
