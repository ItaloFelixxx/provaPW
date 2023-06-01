package br.com.project.suplementos.loja.de.suplementos.repository;


import br.com.project.suplementos.loja.de.suplementos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findUsuarioByLogin(String login);
}
