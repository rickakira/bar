package com.teste.bar.controller;

import com.teste.bar.entity.Marca;
import com.teste.bar.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    MarcaRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Marca>> getAll() {
        List<Marca> marcaList = repository.findAll();

        return marcaList.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(marcaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getById(@PathVariable("id") Long id) {
        Optional<Marca> marca = repository.findById(id);

        return marca.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Marca>> getByDescricao(@PathVariable("descricao") String descricao) {
        List<Marca> marcaLis = repository.findByDescricao(descricao);

        return marcaLis.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(marcaLis);
    }

    @PostMapping("/")
    public ResponseEntity<Marca> save(@RequestBody Marca marca) {
        Marca persisted = repository.save(Marca.builder()
                                               .descricao(marca.getDescricao())
                                               .build());

        return new ResponseEntity<>(persisted, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Marca marca) {
        repository.save(Marca.builder()
                             .id(marca.getId())
                             .descricao(marca.getDescricao())
                             .build());

        return ResponseEntity.ok("Atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.ok("Deletado com sucesso.");
    }
}
