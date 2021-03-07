package com.teste.bar.repository;

import com.teste.bar.entity.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {

    List<TipoProduto> findByDescricao(@Param("descricao") String descricao);

}
