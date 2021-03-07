package com.teste.bar.controller;

import com.teste.bar.entity.Marca;
import com.teste.bar.exception.ResourceNotFoundException;
import com.teste.bar.repository.MarcaRepository;
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
@RequestMapping("/api/marca")
@Tag(name = "marca")
public class MarcaController {

    @Autowired
    MarcaRepository repository;

    @GetMapping("/")
    @Operation(summary = "GET responsável no retorno das marcas cadastradas.",
               description = "Busca e retorna todas as marcas cadastradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Marcas encontradas", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "204", description = "Nenhuma marca encontrada")
    })
    public ResponseEntity<List<Marca>> getAll() {
        List<Marca> marcaList = repository.findAll();

        return marcaList.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(marcaList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "GET responsável no retorno de uma marca por ID",
               description = "Busca e retorna a marca pelo parâmetro de ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca encontrada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Não encontrado nenhuma informação com o ID informado.")
    })
    public ResponseEntity<Marca> getById(@Parameter(description = "Informação do ID", required = true, example = "1")
                                         @PathVariable("id") Long id) {
        Optional<Marca> marca = repository.findById(id);

        return marca.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/descricao/{descricao}")
    @Operation(summary = "GET responsável no retorno de uma marca por descrição",
            description = "Busca e retorna a marca pelo parâmetro de descrição informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca encontrada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Não encontrado nenhuma informação com o ID informado.")
    })
    public ResponseEntity<List<Marca>> getByDescricao(@Parameter(description = "Descrição a ser pesquisada", required = true, example = "Marca")
                                                      @PathVariable("descricao") String descricao) {
        List<Marca> marcaLis = repository.findByDescricao(descricao);

        return marcaLis.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(marcaLis);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Inclusão de marca",
               description = "Responsável na inclusão das informações de Marca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marca criada",
                         content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<Marca> save(@RequestBody Marca marca) {
        Marca persisted = repository.save(Marca.builder()
                                               .descricao(marca.getDescricao())
                                               .build());

        return new ResponseEntity<>(persisted, HttpStatus.CREATED);
    }

    @PutMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Alteração da marca",
                description = "Responsável na alteração das informações da Marca cadastrada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca alterada",
                            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    })
    public ResponseEntity<?> update(@RequestBody Marca marca) {
        repository.save(Marca.builder()
                             .id(marca.getId())
                             .descricao(marca.getDescricao())
                             .build());

        return ResponseEntity.ok("Atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão da marca por ID",
            description = "Responsável na exclusão das informações de Marca pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca excluída",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    })
    public ResponseEntity<?> delete(@Parameter(description = "Informação do ID", required = true, example = "1")
                                    @PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.ok("Deletado com sucesso.");
    }
}
