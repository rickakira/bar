package com.teste.bar.controller;

import com.teste.bar.entity.PrecoProduto;
import com.teste.bar.repository.PrecoProdutoRepository;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/produto/preco")
@Tag(name = "produto")
public class PrecoProdutoController {

    @Autowired
    PrecoProdutoRepository repository;

    @GetMapping("/")
    @Operation(summary = "GET responsável no retorno dos preços cadastrados paginados.",
            description = "Busca e retorna todos os preços cadastrados de forma paginada.")
    @ApiResponse(responseCode = "200", description = "Preços encontrados", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<?> getAll(@Parameter(description = "Número da página. Valor default 0.") @RequestParam(defaultValue = "0") int page,
                                    @Parameter(description = "Quantidade de informações a serem devolvidas. Valor default 10.") @RequestParam(defaultValue = "10") int size) {
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

    @Operation(summary = "GET responsável no retorno de um preço por ID",
            description = "Busca e retorna o preço pelo parâmetro de ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Não encontrado nenhuma informação com o ID informado.")
    })
    public ResponseEntity<PrecoProduto> getById(@Parameter(description = "Informação do ID", required = true, example = "1")
                                           @PathVariable("id") Long id) {
        Optional<PrecoProduto> precoProduto = repository.findById(id);

        return precoProduto.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Inclusão de preço",
            description = "Responsável na inclusão das informações de Preço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Preço criado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<PrecoProduto> save(@RequestBody PrecoProduto produto) {
        PrecoProduto persisted = repository.save(PrecoProduto.builder()
                                                .porcentagemLucro(produto.getPorcentagemLucro())
                                                .precoCusto(produto.getPrecoCusto())
                                                .precoVenda(produto.getPrecoVenda())
                                                .produto(produto.getProduto())
                                                .build());

        return new ResponseEntity<>(persisted, HttpStatus.CREATED);
    }

    @PutMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Alteração do preço",
            description = "Responsável na alteração das informações do Preço Cadastrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço alterado",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    })
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
    @Operation(summary = "Exclusão do preço por ID",
            description = "Responsável na exclusão das informações de Preço pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço excluído",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.ok("Deletado com sucesso.");
    }
}
