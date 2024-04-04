package com.bside.BSIDE.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bside.BSIDE.jwt.exception.CustomAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

/**
 * @WebConfig
 * @작성자 DongHun
 * @일자 2024.01.11.
 **/

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebConfig {	
	
//	순환 참조, 양방향 의존관계 해결 시급
	 private final AuthenticationManagerConfig authenticationManagerConfig;
	 private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf((csrfConfig) ->
				csrfConfig.disable()
			)
			.headers((headerCofig) ->
				headerCofig.frameOptions(frameOptionsConfig ->
					frameOptionsConfig.disable()
				)
			)
			.sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
			.authorizeHttpRequests(authorizeRequests ->
				authorizeRequests
					.requestMatchers("/register", "/login/**").permitAll()
					.requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
					.requestMatchers("/posts/**", "/api/v1/posts/**").hasRole("USER")
					.requestMatchers("/admins/**", "/api/v1/admins/**").hasRole("ADMIN")
					.requestMatchers("/users/**", "/user/**", "/question/**", "/password/**", "/email/**", "/category/**").permitAll()
					.anyRequest().authenticated()
			
			)
			.exceptionHandling((exceptionConfig) ->
				exceptionConfig.authenticationEntryPoint(customAuthenticationEntryPoint)
			)			
			.apply(authenticationManagerConfig); // 필터 구현 후 사용
		
			
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
	    return new BCryptPasswordEncoder();
	}
	
}
