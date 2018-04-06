package br.com.authentication.config;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class CustonAuthenticationServer extends AuthorizationServerConfigurerAdapter {

	@Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
         endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
         clients
	         .inMemory()
	         .withClient("my-client-id") // Client id
	         .authorizedGrantTypes("client_credentials", "password")
	         .scopes("read_write")
	         .secret("my-client-secret"); // Client secret
    }
    
    // Para gerar o Basic Authentication
    public static void main(String[] args) {
    	String clientId = "my-client-id";
    	String clientSecret = "my-client-secret";
    	byte[] encodeBase64 = Base64.encodeBase64((clientId + ":" + clientSecret).getBytes());
		System.out.println(new String(encodeBase64));
	}
	
}
