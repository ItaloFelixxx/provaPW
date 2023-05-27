package br.com.project.suplementos.loja.de.suplementos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Suplemento {
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
}
