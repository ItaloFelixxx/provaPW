package br.com.project.suplementos.loja.de.suplementos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotBlank
    private String marca;
    @Min(0)
    private Long preco;
    private Date deleted;
    private String imageUri;
    private Boolean inSale;

    public Produto(Produto p){
        this.id = p.getId();
        this.nome = p.getNome();
        this.descricao = p.getDescricao();
        this.marca = p.getMarca();
        this.preco = p.getPreco();
        this.deleted = p.getDeleted();
        this.imageUri = p.getImageUri();
        this.inSale = p.getInSale();
    }
}
