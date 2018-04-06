package br.com.authentication.domain;

import java.util.List;

public class Usuario {
	
	private Long id;
	
	private String login;
	
	private String password;
	
	List<UsuarioGrantedAuthority> grantis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UsuarioGrantedAuthority> getGrantis() {
		return grantis;
	}

	public void setGrantis(List<UsuarioGrantedAuthority> grantis) {
		this.grantis = grantis;
	}

}
