package com.devsuperior.dslearnbds.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAccessTokenConverter tokenConverter() {
	  JwtAccessTokenConverter token = new JwtAccessTokenConverter();
	  token.setSigningKey(jwtSecret);
	  return token;
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenConverter());
	}
	
}
