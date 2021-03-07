package com.teste.bar.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Data
@Entity
@Table(name = "MARCA")
@AllArgsConstructor
@Schema(name="Marca", description = "POJO representando a entidade Marca. Idealmente, seria melhor a quebra entre DTO e entity, mas o tempo dado não permitiu esse tipo de abordagem melhorada.")
@EqualsAndHashCode
public class Marca implements Serializable {

    private static final long serialVersionUID = 8268163756911224955L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Identificador da Marca. Utilizado para a realização de atualização da informação",
            name = "id")
    private Long id;

    @Column(name = "DESCRICAO", length = 100)
    @NotNull @NotBlank @Size(min = 1, max = 100)
    @Schema(description = "Descrição da Marca. Campo de preenchimento obrigatório. Possui quantidade mínima de 1 e máxima de 100 caracteres",
            example = "Marca", required = true, minLength = 1, maxLength = 100, name = "descricao")
    private String descricao;
}
