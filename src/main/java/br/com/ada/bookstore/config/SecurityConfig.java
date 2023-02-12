package br.com.ada.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.ada.bookstore.filter.AuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private AuthenticationFilter authenticationFilter;
	
	@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
        		.csrf().disable() // desabilita controle de sessão
    				.sessionManagement()
    					.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // desabilita a criação de sessão 
        		.and()
        		.authorizeHttpRequests()
        			.requestMatchers(HttpMethod.POST, "/usuarios","/usuarios/auth").permitAll()
        			.requestMatchers(HttpMethod.GET, "/usuarios/auth/**").permitAll()
    				.requestMatchers(PathRequest.toH2Console()).permitAll()
    				.anyRequest().authenticated()
        		.and()
        		.headers().frameOptions().disable()
        		.and()
        		.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
        		.build();
    }
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();	
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authConfig) {
		try {
			return authConfig.getAuthenticationManager();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
