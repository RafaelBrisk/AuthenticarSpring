package br.com.authentication.Authentication.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthentication implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName().toString();
		
		if (username == null) {
			throw new BadCredentialsException("Precisa de usuário.");
		}
		
		Object credeciales = authentication.getCredentials();
		
		if (credeciales != null) {
			String password = credeciales.toString();
			
			if (username.equals("usuario")) {
				
				if (password.equals("admin")) {
					List<GrantedAuthority> grantis = new ArrayList<>();
					grantis.add(() -> "ROLE_ADMIN");
					return new UsernamePasswordAuthenticationToken(username, password, grantis);
				}
				
			} else {
				throw new BadCredentialsException("Usuário não cadastrado.");
			}
		}
		
		throw new BadCredentialsException("Precisa de senha.");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
