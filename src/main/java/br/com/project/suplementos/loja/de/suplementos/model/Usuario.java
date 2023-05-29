package br.com.project.suplementos.loja.de.suplementos.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String nome;
    String cpf;
    String login;
    String senha;
    Boolean isAdmin;
}
