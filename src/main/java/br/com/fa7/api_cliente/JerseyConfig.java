package br.com.fa7.api_cliente;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import br.com.fa7.api_cliente.resource.ClienteResource;
import br.com.fa7.api_cliente.resource.EnderecoResource;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(ClienteResource.class);
		register( EnderecoResource.class);
	}
	
}
