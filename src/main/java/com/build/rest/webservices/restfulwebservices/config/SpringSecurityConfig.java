package com.build.rest.webservices.restfulwebservices.config;

import  static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception
	{
//		all requests should be authenticated
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
//		if request is not authenticated shows web page for authentication
		http.httpBasic(withDefaults());
//		enables post, put
		http.csrf().disable();
		return http.build();
		
		
	}
}
