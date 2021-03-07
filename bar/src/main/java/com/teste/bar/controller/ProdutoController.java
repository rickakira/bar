package com.teste.bar.controller;

import com.teste.bar.entity.Produto;
import com.teste.bar.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Validated
@RestController
@RequestMapping("/api/produto")
@Tag(name = "produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping("/")
    @Operation(summary = "GET responsável no retorno dos produtos cadastrados paginados.",
            description = "Busca e retorna todos os produtos cadastrados de forma paginada.")
    @ApiResponse(responseCode = "200", description = "Produtos encontrados", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<?> getAll(@Parameter(description = "Número da página. Valor default 0.") @RequestParam(defaultValue = "0") int page,
                                    @Parameter(description = "Quantidade de informações a serem devolvidas. Valor default 10.") @RequestParam(defaultValue = "10") int size) {
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
    @Operation(summary = "GET responsável no retorno de um produto por ID",
            description = "Busca e retorna o produto pelo parâmetro de ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Não encontrado nenhuma informação com o ID informado.")
    })
    public ResponseEntity<Produto> getById(@Parameter(description = "Informação do ID", required = true, example = "1")
                                               @PathVariable("id") Long id) {
        Optional<Produto> marca = repository.findById(id);

        return marca.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/descricao/{descricao}")
    @Operation(summary = "GET responsável no retorno de um produto por descrição",
            description = "Busca e retorna o produto pelo parâmetro de descrição informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Não encontrado nenhuma informação com o ID informado.")
    })
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

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Inclusão de produto",
            description = "Responsável na inclusão das informações de Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        Produto persisted = repository.save(Produto.builder()
                                               .descricao(produto.getDescricao())
                                               .marca(produto.getMarca())
                                               .tipoProduto(produto.getTipoProduto())
                                               .build());

        return new ResponseEntity<>(persisted, HttpStatus.CREATED);
    }

    @PutMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Alteração do produto",
            description = "Responsável na alteração das informações do Produto Cadastrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto alterado",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    })
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
    @Operation(summary = "Exclusão do produto por ID",
            description = "Responsável na exclusão das informações de Produto pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto excluído",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.ok("Deletado com sucesso.");
    }
}
