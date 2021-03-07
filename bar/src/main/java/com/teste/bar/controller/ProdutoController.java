package com.teste.bar.controller;

import com.teste.bar.entity.Produto;
import com.teste.bar.repository.ProdutoRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping("/")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        List<Produto> produtoList = Collections.emptyList();
        Pageable paginado = PageRequest.of(page, size);

        Page<Produto> produtoPage = repository.findAll(paginado);

        produtoList = produtoPage.getContent();

        val response = new HashMap<String, Object>();
        response.put("produtos", produtoList);
        response.put("paginaAtual", produtoPage.getNumber());
        response.put("totalItems", produtoPage.getTotalElements());
        response.put("totalPaginas", produtoPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable("id") Long id) {
        Optional<Produto> marca = repository.findById(id);

        return marca.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<?> getByDescricao(@PathVariable("descricao") String descricao,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        List<Produto> produtoList = Collections.emptyList();
        Pageable paginado = PageRequest.of(page, size);

        Page<Produto> produtoPage = repository.findByDescricao(descricao, paginado);

        produtoList = produtoPage.getContent();

        val response = new HashMap<String, Object>();
        response.put("produtos", produtoList);
        response.put("paginaAtual", produtoPage.getNumber());
        response.put("totalItems", produtoPage.getTotalElements());
        response.put("totalPaginas", produtoPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        Produto persisted = repository.save(Produto.builder()
                                               .descricao(produto.getDescricao())
                                               .marca(produto.getMarca())
                                               .tipoProduto(produto.getTipoProduto())
                                               .build());

        return new ResponseEntity<>(persisted, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Produto produto) {
        repository.save(Produto.builder()
                             .id(produto.getId())
                             .descricao(produto.getDescricao())
                             .marca(produto.getMarca())
                             .tipoProduto(produto.getTipoProduto())
                             .build());

        return ResponseEntity.ok("Atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.ok("Deletado com sucesso.");
    }
}
