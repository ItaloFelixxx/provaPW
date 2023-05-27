package br.com.project.suplementos.loja.de.suplementos.repository;


import br.com.project.suplementos.loja.de.suplementos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findUsuarioByLogin(String login);
}
