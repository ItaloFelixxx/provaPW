package br.com.project.suplementos.loja.de.suplementos.service;

import br.com.project.suplementos.loja.de.suplementos.model.Usuario;
import br.com.project.suplementos.loja.de.suplementos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService{

    UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void create(Usuario u){
        this.repository.save(u);
    }

    public List<Usuario> listAll(){
        return  repository.findAll();
    }
}