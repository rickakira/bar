package com.teste.bar.repository;

import com.teste.bar.entity.PrecoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecoProdutoRepository extends JpaRepository<PrecoProduto, Long> {
}
