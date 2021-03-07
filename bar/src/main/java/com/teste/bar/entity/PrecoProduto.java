package com.teste.bar.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Data
@Entity
@Table(name = "PRECO_PRODUTO")
@AllArgsConstructor
public class PrecoProduto implements Serializable {

    private static final long serialVersionUID = -9160943553598812587L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;

    @Column(name = "PORCENTAGEM_LUCRO")
    private Long porcentagemLucro;

    @Column(name = "PRECO_CUSTO", precision = 10, scale = 2)
    @NotNull
    private BigDecimal precoCusto;

    @Column(name = "PRECO_VENDA", precision = 10, scale = 2)
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
