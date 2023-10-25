package br.com.Especialista.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.Especialista.Security.JWTAuthorizationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig   {
	
	private static final String[] AUTH_WHITELIST = {
			   "/api/login", 
				"/swagger-resources/**",
		        "/webjars/**",
		        "/v2/**",
		        "/swagger-ui/**",
		        "/swagger-ui.html",
		        "/api/usuarios",
		        "/**" //remover 
	};
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.cors().and()
		.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeHttpRequests().requestMatchers(AUTH_WHITELIST).permitAll()
		.and()
		.authorizeHttpRequests().anyRequest().authenticated();
//		.and()
//		.httpBasic();
		return http.build();
	}
	
}