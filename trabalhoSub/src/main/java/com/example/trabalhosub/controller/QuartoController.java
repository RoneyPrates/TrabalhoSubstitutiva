package com.example.trabalhosub.controller;

import com.example.trabalhosub.model.Quarto;
import com.example.trabalhosub.service.QuartoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quarto")
public class QuartoController {

    private final QuartoService quartoService;

    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    // Endpoint para buscar todos os quartos
    @GetMapping
    public ResponseEntity<List<Quarto>> getAllQuartos() {
        List<Quarto> quartos = quartoService.getAll();
        return ResponseEntity.ok(quartos);
    }

    // Endpoint para buscar um quarto pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Quarto> getQuartoById(@PathVariable int id) {
        Optional<Quarto> quarto = quartoService.getById(id);
        return quarto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para cadastrar um novo quarto
    @PostMapping
    public ResponseEntity<Quarto> cadastrarQuarto(@RequestBody Quarto quarto) {
        Quarto novoQuarto = quartoService.save(quarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoQuarto);
    }

    // Endpoint para atualizar um quarto existente
    @PutMapping("/{id}")
    public ResponseEntity<Quarto> atualizarQuarto(@PathVariable int id, @RequestBody Quarto quartoAtualizado) {
        Optional<Quarto> quarto = quartoService.getById(id);
        if (quarto.isPresent()) {
            quartoAtualizado.setId(id); // Garantir que o ID do quarto atualizado seja o mesmo do quarto existente
            Quarto quartoAtualizadoDb = quartoService.save(quartoAtualizado);
            return ResponseEntity.ok(quartoAtualizadoDb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para deletar um quarto pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarQuarto(@PathVariable int id) {
        Optional<Quarto> quarto = quartoService.getById(id);
        if (quarto.isPresent()) {
            quartoService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para buscar quartos disponíveis
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Quarto>> getQuartosDisponiveis() {
        List<Quarto> quartosDisponiveis = quartoService.findQuartosDisponiveis();
        return ResponseEntity.ok(quartosDisponiveis);
    }

    // Endpoint para buscar quartos por quantidade de ocupantes
    @GetMapping("/porQuantidadeOcupantes/{quantidade}")
    public ResponseEntity<List<Quarto>> getQuartosPorQuantidadeOcupantes(@PathVariable int quantidade) {
        List<Quarto> quartos = quartoService.findQuartosByQuantidadeOcupantes(quantidade);
        return ResponseEntity.ok(quartos);
    }

    // Endpoint para buscar todos os quartos ocupados
    @GetMapping("/ocupados")
    public ResponseEntity<List<Quarto>> getQuartosOcupados() {
        List<Quarto> quartosOcupados = quartoService.findQuartosDisponiveis();
        return ResponseEntity.ok(quartosOcupados);
    }

    // Endpoint para buscar todos os quartos com vista para o mar disponíveis
    @GetMapping("/comVistaMar")
    public ResponseEntity<List<Quarto>> getQuartosComVistaMarDisponiveis() {
        List<Quarto> quartosComVistaMar = quartoService.findQuartosComVistaMarDisponiveis();
        return ResponseEntity.ok(quartosComVistaMar);
    }
}
