package br.com.project.suplementos.loja.de.suplementos.service;

import br.com.project.suplementos.loja.de.suplementos.model.Usuario;
import br.com.project.suplementos.loja.de.suplementos.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    UsuarioRepository repository;
    BCryptPasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public void create(Usuario u){
        u.setSenha(encoder.encode(u.getSenha()));
        this.repository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = repository.findUsuarioByLogin(username);
        if (user != null){
            System.out.println(user.getAuthorities());
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            return user;
        }else{
            throw new UsernameNotFoundException("Username not found");
        }
    }

    public List<Usuario> listAll(){
        return repository.findAll();
    }
}