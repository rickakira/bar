package com.teste.bar.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Data
@Entity
@Table(name = "PRODUTO")
@AllArgsConstructor
@Schema(name="Produto", description = "POJO representando a entidade Produto. Idealmente, seria melhor a quebra entre DTO e entity, mas o tempo dado não permitiu esse tipo de abordagem melhorada.")
public class Produto implements Serializable {

    private static final long serialVersionUID = 3351474170673778244L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Identificador do Produto. Utilizado para a realização de atualização da informação",
            name = "id")
    private Long id;

    @Column(name = "DESCRICAO", length = 100)
    @NotNull @NotBlank
    @Size(min = 1, max = 100)
    @Schema(description = "Descrição do Produto. Campo de preenchimento obrigatório. Possui quantidade mínima de 1 e máxima de 100 caracteres",
            example = "IPA 500ml", required = true, minLength = 1, maxLength = 100, name = "descricao")
    private String descricao;

    @ManyToOne @JoinColumn(name = "ID_TIPO_PRODUTO")
    @NotNull
    @Schema(description = "Informações referente ao Tipo de Produto. Campo de preenchimento obrigatório. " +
                          "Nesse cenário, seria interessante uma quebra no input do tipo de informação, " +
                          "não recebendo objetos complexos desnecessários, onde o envio do ID já seria suficiente. " +
                          "Melhorias que seriam bem-vindas com um tempo para desenvolvimento maior",
                          required = true, name = "tipoProduto")
    private TipoProduto tipoProduto;

    @Schema(description = "Informações referente à Marca. Campo de preenchimento obrigatório. " +
            "Nesse cenário, seria interessante uma quebra no input do tipo de informação, " +
            "não recebendo objetos complexos desnecessários, onde o envio do ID já seria suficiente. " +
            "Melhorias que seriam bem-vindas com um tempo para desenvolvimento maior",
            required = true, name = "marca")
    @ManyToOne @JoinColumn(name = "ID_MARCA")
    private Marca marca;

}
