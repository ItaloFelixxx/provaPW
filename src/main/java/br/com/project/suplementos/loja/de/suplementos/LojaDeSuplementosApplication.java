package br.com.project.suplementos.loja.de.suplementos;


import br.com.project.suplementos.loja.de.suplementos.model.Usuario;
import br.com.project.suplementos.loja.de.suplementos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
public class LojaDeSuplementosApplication implements WebMvcConfigurer {
	// Criação dos usuarios e o adm
	@Bean
	CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, BCryptPasswordEncoder encoder) {
		return args -> {
			List<Usuario> users = new ArrayList<>();
			Usuario admin = new Usuario(1,"ADMINISTRADOR","012.345.678-10","admin", encoder.encode("admin"),true);
			Usuario user1 = new Usuario(2,"João","110.001.110.00","user1", encoder.encode( "user1"),false);
			Usuario user2 = new Usuario(3,"Maria","000.000.111.22","user2",encoder.encode("user2"),false);
			users.add(admin);
			users.add(user1);
			users.add(user2);
			usuarioRepository.saveAll(users);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(LojaDeSuplementosApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");

	}
}
