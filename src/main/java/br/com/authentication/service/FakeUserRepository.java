package br.com.authentication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.authentication.domain.Usuario;
import br.com.authentication.domain.UsuarioGrantedAuthority;

@Service
public class FakeUserRepository {
	
	public Usuario findByLogin(String login) {
		if (login == "usuario") {
			Usuario usuario = new Usuario();
			usuario.setId(1L);
			usuario.setLogin("usuario");
			usuario.setPassword("senha");
			
			List<UsuarioGrantedAuthority> usuarioGrantedAuthorities = new ArrayList<>();
			
			UsuarioGrantedAuthority usuarioGrantedAuthority = new UsuarioGrantedAuthority();
			usuarioGrantedAuthority.setId(1L);
			usuarioGrantedAuthority.setRole("ROLE_ADMIN");
			
			usuarioGrantedAuthorities.add(usuarioGrantedAuthority);
			
			usuario.setGrantis(usuarioGrantedAuthorities);
			
			return usuario;
		}
		
		return null;
	}
	

}
