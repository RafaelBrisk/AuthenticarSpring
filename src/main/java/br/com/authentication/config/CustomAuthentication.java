package br.com.authentication.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.authentication.domain.Usuario;
import br.com.authentication.domain.UsuarioGrantedAuthority;
import br.com.authentication.service.FakeUserRepository;

@Component
public class CustomAuthentication implements AuthenticationProvider {
	
	@Autowired @Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private FakeUserRepository fakeUserRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName().toString();
		
		if (username == null) {
			throw new BadCredentialsException("Precisa de usuário.");
		}
		
		Object credeciales = authentication.getCredentials();
		
		if (credeciales != null) {
			String password = credeciales.toString();
			
			System.out.println("\n\n" + password + "\n\n");
			
			Usuario usuario = fakeUserRepository.findByLogin(username);
			
			if (usuario == null) {
				throw new UsernameNotFoundException("Usuário " + username + " não cadastrado.");
			}
			
			String passwordCodificado = passwordEncoder.encode(password);
			
			if (passwordEncoder.matches(passwordCodificado, passwordEncoder.encode(usuario.getPassword()))) {
				List<GrantedAuthority> grantis = new ArrayList<>();
				
				List<UsuarioGrantedAuthority> usuarioGrantedAuthorities = usuario.getGrantis();
				
				for (UsuarioGrantedAuthority usuarioGrantedAuthority : usuarioGrantedAuthorities) {
					grantis.add(() -> usuarioGrantedAuthority.getRole());
				}
				
				return new UsernamePasswordAuthenticationToken(username, password, grantis);
			}
		}
		
		throw new BadCredentialsException("Senha incorreta.");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
