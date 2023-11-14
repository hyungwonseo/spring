package com.dw.jwtauthority.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.dw.jwtauthority.jwt.JwtAccessDeniedHandler;
import com.dw.jwtauthority.jwt.JwtAuthenticationEntryPoint;
import com.dw.jwtauthority.jwt.JwtSecurityConfig;
import com.dw.jwtauthority.jwt.TokenProvider;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	private final TokenProvider tokenProvider;
//    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	public SecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
			JwtAccessDeniedHandler jwtAccessDeniedHandler) {
		super();
		this.tokenProvider = tokenProvider;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			
//			.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            )
            
			.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
					.requestMatchers("/api/hello").permitAll()
					.requestMatchers("/api/authenticate").permitAll()
					.requestMatchers("/api/signup").permitAll()
					.anyRequest().authenticated()
			)
			
			.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
					SessionCreationPolicy.STATELESS))
			
			.formLogin(formLoginCustomizer -> formLoginCustomizer.defaultSuccessUrl("/"))
			
			.apply(new JwtSecurityConfig(tokenProvider));
		
		return http.build();
	}
}
