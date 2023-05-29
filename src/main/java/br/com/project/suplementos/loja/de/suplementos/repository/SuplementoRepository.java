package br.com.project.suplementos.loja.de.suplementos.repository;

import br.com.project.suplementos.loja.de.suplementos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SuplementoRepository extends JpaRepository<Produto, String> {
    Optional<Produto> findSuplementoByMarca(String Marca);

    Optional<Produto> findSuplementoById(String Id);

    List<Produto> findByDeletedIsNull();

    List<Produto> findByInSaleIsTrue();
}
