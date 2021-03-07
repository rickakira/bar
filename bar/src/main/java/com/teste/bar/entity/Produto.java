package com.teste.bar.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@Entity
@Table(name = "PRODUTO")
@AllArgsConstructor
public class Produto implements Serializable {

    private static final long serialVersionUID = 3351474170673778244L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DESCRICAO", length = 100) @NotNull
    private String descricao;

    @ManyToOne @JoinColumn(name = "ID_TIPO_PRODUTO")
    private TipoProduto tipoProduto;

    @ManyToOne @JoinColumn(name = "ID_MARCA")
    private Marca marca;

}
