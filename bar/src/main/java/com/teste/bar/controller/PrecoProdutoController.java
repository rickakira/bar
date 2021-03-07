package com.teste.bar.controller;

import com.teste.bar.entity.PrecoProduto;
import com.teste.bar.repository.PrecoProdutoRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produto/preco")
public class PrecoProdutoController {

    @Autowired
    PrecoProdutoRepository repository;

    @GetMapping("/")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        List<PrecoProduto> precoProdutoList = Collections.emptyList();
        Pageable paginado = PageRequest.of(page, size);

        Page<PrecoProduto> produtoPage = repository.findAll(paginado);

        precoProdutoList = produtoPage.getContent();

        val response = new HashMap<String, Object>();
        response.put("produtos", precoProdutoList);
        response.put("paginaAtual", produtoPage.getNumber());
        response.put("totalItems", produtoPage.getTotalElements());
        response.put("totalPaginas", produtoPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrecoProduto> getById(@PathVariable("id") Long id) {
        Optional<PrecoProduto> precoProduto = repository.findById(id);

        return precoProduto.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<PrecoProduto> save(@RequestBody PrecoProduto produto) {
        PrecoProduto persisted = repository.save(PrecoProduto.builder()
                                                .porcentagemLucro(produto.getPorcentagemLucro())
                                                .precoCusto(produto.getPrecoCusto())
                                                .precoVenda(produto.getPrecoVenda())
                                                .produto(produto.getProduto())
                                                .build());

        return new ResponseEntity<>(persisted, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody PrecoProduto produto) {
        repository.save(PrecoProduto.builder()
                                .id(produto.getId())
                                .porcentagemLucro(produto.getPorcentagemLucro())
                                .precoCusto(produto.getPrecoCusto())
                                .precoVenda(produto.getPrecoVenda())
                                .produto(produto.getProduto())
                                .build());

        return ResponseEntity.ok("Atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.ok("Deletado com sucesso.");
    }
}
