package com.teste.SegundoAPI.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.teste.SegundoAPI.model.RegintroTempo;
import com.teste.SegundoAPI.service.TempoService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/tempos")
public class TempoCotroller {

    private final TempoService tempoService;

    public TempoCotroller(TempoService tempoService) {
        this.tempoService = tempoService;
    }

    @GetMapping
    public List<RegintroTempo> listarTodo() {
        return tempoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegintroTempo> buscaPorId(@PathVariable Long id) {
        return tempoService.buscaId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RegintroTempo> salva(@RequestBody RegintroTempo registro) {
        RegintroTempo salvo = tempoService.salva(registro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();
        return ResponseEntity.created(uri).body(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (tempoService.buscaId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tempoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
