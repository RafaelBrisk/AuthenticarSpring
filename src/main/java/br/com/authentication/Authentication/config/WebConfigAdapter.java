package br.com.authentication.Authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebConfigAdapter extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthentication authentication;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http
			.cors()
			.and()
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/api/**").authenticated()
			.anyRequest().authenticated()
			.and()
			.logout()
			.logoutUrl("/logout")
			;
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authentication);
	}

}
