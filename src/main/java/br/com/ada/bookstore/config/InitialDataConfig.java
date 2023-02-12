package br.com.ada.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.ada.bookstore.model.entity.Perfil;
import br.com.ada.bookstore.repository.PerfilRepository;

@Configuration
public class InitialDataConfig {

	@Autowired
	private PerfilRepository repository;
	
	@Bean
	public void inserirDados() {
		final Perfil perfilUm = Perfil.builder().nome("ROLE_ADMIN").descricao("Perfil de administrador").build();
		final Perfil perfilDois = Perfil.builder().nome("ROLE_USER").descricao("Perfil de usuário comum").build();
		repository.save(perfilUm);
		repository.save(perfilDois);
	}

}
