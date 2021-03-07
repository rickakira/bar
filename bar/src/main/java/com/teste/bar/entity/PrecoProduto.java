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
import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Data
@Entity
@Table(name = "PRECO_PRODUTO")
@AllArgsConstructor
@Schema(name="PrecoProduto", description = "POJO representando a entidade PrecoProduto. Idealmente, seria melhor a quebra entre DTO e entity, mas o tempo dado não permitiu esse tipo de abordagem melhorada.")
public class PrecoProduto implements Serializable {

    private static final long serialVersionUID = -9160943553598812587L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Identificador do Produto. Utilizado para a realização de atualização da informação",
            name = "id")
    private Long id;

    @OneToOne @JoinColumn(name = "ID_PRODUTO")
    @NotNull
    @Schema(description = "Informações referente ao Produto. Campo de preenchimento obrigatório. " +
            "Nesse cenário, seria interessante uma quebra no input do tipo de informação, " +
            "não recebendo objetos complexos desnecessários, onde o envio do ID já seria suficiente. " +
            "Melhorias que seriam bem-vindas com um tempo para desenvolvimento maior",
            required = true, name = "produto")
    private Produto produto;

    @Column(name = "PORCENTAGEM_LUCRO")
    @Size(max = 100)
    @Schema(description = "Porcentagem de Lucro. Utilizado para informar qual a porcentagem de lucro baseado no preço de Custo. " +
                          "Caso o campo Preço de Venda esteja preenchido, essa informação não é utilizada, caso contrário é realizada o cálculo: " +
                          "precoCusto * ( porcentagemLucro / 100 + 1) ",
            maxLength = 100, name = "porcentagemLucro")
    private Long porcentagemLucro;

    @Column(name = "PRECO_CUSTO", precision = 10, scale = 2)
    @NotNull
    @Schema(description = "Preço de Custo do Produto. Campo de preenchimento obrigatório",
            name = "precoCusto", required = true)
    private BigDecimal precoCusto;

    @Column(name = "PRECO_VENDA", precision = 10, scale = 2)
    @Schema(description = "Preço de Venda do Produto.",
            name = "precoVenda")
    private BigDecimal precoVenda;

    public BigDecimal getPrecoVenda() {
        if (Objects.nonNull(this.precoVenda)) {
            return this.precoVenda;
        }
        if (Objects.nonNull(this.porcentagemLucro)) {
            return this.precoCusto.multiply(convertePorcentagem());
        }

        return BigDecimal.ZERO;
    }

    private BigDecimal convertePorcentagem() {
        return BigDecimal.valueOf(this.porcentagemLucro)
                         .divide(new BigDecimal(100))
                         .add(new BigDecimal(1));
    }
}
