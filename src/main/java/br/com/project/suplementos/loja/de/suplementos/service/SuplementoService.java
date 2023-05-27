package br.com.project.suplementos.loja.de.suplementos.service;

import br.com.project.suplementos.loja.de.suplementos.model.Suplemento;
import br.com.project.suplementos.loja.de.suplementos.repository.SuplementoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuplementoService implements UserDetailsService{
    SuplementoRepository repository;

    public SuplementoService(SuplementoRepository repository){
        this.repository = repository;
    }

    public void create(Suplemento suplemento){
        repository.save(suplemento);
    }

    @Override
    public UserDetails loadUserByUsername(String marca) throws UsernameNotFoundException {
        Optional<Suplemento> suplemento = repository.findSuplementoByMarca(marca);
        if (suplemento.isPresent()){
            return (UserDetails) suplemento.get();
        }else{
            throw new UsernameNotFoundException("Username not found");
        }
    }
}
