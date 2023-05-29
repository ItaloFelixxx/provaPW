package br.com.project.suplementos.loja.de.suplementos.service;

import br.com.project.suplementos.loja.de.suplementos.model.Produto;
import br.com.project.suplementos.loja.de.suplementos.repository.SuplementoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SuplementoService{
    SuplementoRepository repository;

    public SuplementoService(SuplementoRepository repository){
        this.repository = repository;
    }

    public int tamanhoCarrinho(List<Produto> lista){
        return lista.size();
    }

    public List<Produto> getItensNaoDeletados() {
        return repository.findByDeletedIsNull();
    }
    public void excluirItem(Produto produto) {
        produto.setDeleted(LocalDate.now());
        repository.save(produto);
    }

    public List<Produto> listarOfertas(){
        return repository.findByInSaleIsTrue();
    }

    public Optional<Produto> buscarPeloId(String id){
        return repository.findSuplementoById(id);
    }

    public void create(Produto produto){
        repository.save(produto);
    }

    public List<Produto> listarTodos(){
        return repository.findAll();
    }
}
