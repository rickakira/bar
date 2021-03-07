package com.teste.bar.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TIPO_PRODUTO")
@Builder
@Schema(name="TipoProduto", description = "POJO representando a entidade TipoProduto. Idealmente, seria melhor a quebra entre DTO e entity, mas o tempo dado não permitiu esse tipo de abordagem melhorada.")
public class TipoProduto implements Serializable {

    private static final long serialVersionUID = -1694223437151562448L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Identificador do Tipo de Produto. Utilizado para a realização de atualização da informação",
            name = "id")
    private Long id;

    @Column(name = "DESCRICAO", length = 100)
    @NotNull @NotBlank
    @Size(min = 1, max = 100)
    @Schema(description = "Descrição do Tipo de Produto. Campo de preenchimento obrigatório. Possui quantidade mínima de 1 e máxima de 100 caracteres",
            example = "Cerveja", required = true, minLength = 1, maxLength = 100, name = "descricao")
    private String descricao;
}
