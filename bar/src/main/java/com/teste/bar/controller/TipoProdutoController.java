package com.teste.bar.controller;

import com.teste.bar.entity.TipoProduto;
import com.teste.bar.repository.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produto/tipo")
public class TipoProdutoController {

    @Autowired
    TipoProdutoRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<TipoProduto>> getAll() {
        List<TipoProduto> tipoList = repository.findAll();

        return tipoList.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(tipoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProduto> getById(@PathVariable("id") Long id) {
        Optional<TipoProduto> produto = repository.findById(id);

        return produto.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<TipoProduto>> getByDescricao(@PathVariable("descricao") String descricao) {
        List<TipoProduto> produtoList = repository.findByDescricao(descricao);

        return produtoList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(produtoList);
    }

    @PostMapping("/")
    public ResponseEntity<TipoProduto> save(@RequestBody TipoProduto tipoProduto) {
        TipoProduto produto = repository.save(TipoProduto.builder()
                                                         .descricao(tipoProduto.getDescricao())
                                                         .build());

        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody TipoProduto tipoProduto) {
        repository.save(TipoProduto.builder()
                                   .id(tipoProduto.getId())
                                   .descricao(tipoProduto.getDescricao())
                                   .build());

        return ResponseEntity.ok("Atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.ok("Deletado com sucesso.");
    }
}
