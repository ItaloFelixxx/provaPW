package br.com.project.suplementos.loja.de.suplementos.repository;

import br.com.project.suplementos.loja.de.suplementos.model.Suplemento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuplementoRepository extends JpaRepository<Suplemento, String> {
    Optional<Suplemento> findSuplementoByMarca(String Marca);
}
