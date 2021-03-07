package com.teste.bar.controller;

import com.teste.bar.entity.TipoProduto;
import com.teste.bar.repository.TipoProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/produto/tipo")
@Tag(name = "produto")
public class TipoProdutoController {

    @Autowired
    TipoProdutoRepository repository;

    @GetMapping("/")
    @Operation(summary = "GET responsável no retorno dos tipos de produtos cadastrados.",
            description = "Busca e retorna todos os tipos de produtos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipos de produtos encontrados", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "204", description = "Nenhum tipo de produto encontrado")
    })
    public ResponseEntity<List<TipoProduto>> getAll() {
        List<TipoProduto> tipoList = repository.findAll();

        return tipoList.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(tipoList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "GET responsável no retorno de um tipo por ID",
            description = "Busca e retorna o tipo pelo parâmetro de ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de produto encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Não encontrado nenhuma informação com o ID informado.")
    })
    public ResponseEntity<TipoProduto> getById(@Parameter(description = "Informação do ID", required = true, example = "1")
                                               @PathVariable("id") Long id) {
        Optional<TipoProduto> produto = repository.findById(id);

        return produto.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/descricao/{descricao}")
    @Operation(summary = "GET responsável no retorno de um tipo por descrição",
            description = "Busca e retorna o tipo pelo parâmetro de descrição informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Não encontrado nenhuma informação com o ID informado.")
    })
    public ResponseEntity<List<TipoProduto>> getByDescricao(@Parameter(description = "Descrição a ser pesquisada", required = true, example = "Tipo")
                                                            @PathVariable("descricao") String descricao) {
        List<TipoProduto> produtoList = repository.findByDescricao(descricao);

        return produtoList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(produtoList);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Inclusão de tipo",
            description = "Responsável na inclusão das informações de Tipo de Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo criado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<TipoProduto> save(@RequestBody TipoProduto tipoProduto) {
        TipoProduto produto = repository.save(TipoProduto.builder()
                                                         .descricao(tipoProduto.getDescricao())
                                                         .build());

        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Alteração do tipo",
            description = "Responsável na alteração das informações do Tipo Cadastrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo alterado",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    })
    public ResponseEntity<?> update(@RequestBody TipoProduto tipoProduto) {
        repository.save(TipoProduto.builder()
                                   .id(tipoProduto.getId())
                                   .descricao(tipoProduto.getDescricao())
                                   .build());

        return ResponseEntity.ok("Atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão do tipo por ID",
            description = "Responsável na exclusão das informações de Tipo de Produto pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo excluído",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    })
    public ResponseEntity<?> delete(@Parameter(description = "Informação do ID", required = true, example = "1")
                                    @PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.ok("Deletado com sucesso.");
    }
}
