package com.teste.bar.entity;

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
@Table(name = "MARCA")
@AllArgsConstructor
public class Marca implements Serializable {

    private static final long serialVersionUID = 8268163756911224955L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DESCRICAO", length = 100)
    @NotNull @NotBlank @Size(min = 1, max = 100)
    private String descricao;
}
