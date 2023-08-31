package br.com.Especialista.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.Especialista.Security.JWTAuthorizationFilter;



@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfiguration {

	
	private static final String[] AUTH_WHITELIST = {
	        "/api/login", 
			"/swagger-resources/**",
	        "/webjars/**",
	        "/v2/**",
	        "/swagger-ui/**",
	        "/swagger-ui.html"
	       
	       
	};

	 @Bean
	    public PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeHttpRequests((x) -> {
			x.requestMatchers(AUTH_WHITELIST).permitAll();
			x.anyRequest().hasAuthority("ACESSO UNICO");
			x.requestMatchers("/api/clientes/**","/api/pedidos/**").hasAnyRole("USER", "ADMIN");
		})
		.cors(x -> {})
		.csrf((csrf) -> csrf.disable());
		return http.build();
	}

}
