package br.com.project.suplementos.loja.de.suplementos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    @NotBlank(message = "O preço não pode ser igual ou menor a zero")
    @Min(0)
    private Long preco;
    @NotBlank
    private Date deleted;
    @NotBlank
    private String imageUri;
    @NotBlank
    private Boolean inSale;

    public void setDeleted(LocalDate now) {
        LocalDate localDate = now; //Obtem a data atual
        // Converte o LocalDate em LocalDateTime
        LocalDateTime localDateTime = localDate.atStartOfDay();
        // Converte o LocalDateTime em Instant
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        // Converte o Instant em Date
        this.deleted = Date.from(instant);
    }

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
