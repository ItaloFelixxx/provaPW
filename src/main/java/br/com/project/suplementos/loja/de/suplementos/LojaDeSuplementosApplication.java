package br.com.project.suplementos.loja.de.suplementos;

import br.com.project.suplementos.loja.de.suplementos.model.Usuario;
import br.com.project.suplementos.loja.de.suplementos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class LojaDeSuplementosApplication {
	@Bean
	CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
		return args -> {
			Collection<Usuario> users = Stream.of(
					new Usuario("", "João", "123.456.789-10", "admin", encoder.encode("admin"), true),
					new Usuario("", "Maria", "444.456.789-10", "user1", encoder.encode("user1"), true),
					new Usuario("", "Pedro", "555.456.789-10", "user2", encoder.encode("user2"), true)
			).collect(Collectors.toList());

			for (var e : users) {
				System.out.println(e);
			}
			usuarioRepository.saveAll(users);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(LojaDeSuplementosApplication.class, args);
	}

}
