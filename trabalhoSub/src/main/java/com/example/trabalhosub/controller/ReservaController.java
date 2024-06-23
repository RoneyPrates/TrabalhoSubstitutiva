package com.example.trabalhosub.controller;

import com.example.trabalhosub.model.Reserva;
import com.example.trabalhosub.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // Endpoint para buscar todas as reservas
    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaService.getAll();
        return ResponseEntity.ok(reservas);
    }

    // Endpoint para buscar uma reserva pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.getById(id);
        return reserva.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para buscar todas as reservas de um determinado hóspede
    @GetMapping("/porHospede/{hospedeId}")
    public ResponseEntity<List<Reserva>> getReservasPorHospede(@PathVariable Long hospedeId) {
        List<Reserva> reservas = reservaService.getByHospedeId(hospedeId);
        return ResponseEntity.ok(reservas);
    }

    // Endpoint para buscar todas as reservas com base na data de entrada
    @GetMapping("/porDataEntrada/{dataEntrada}")
    public ResponseEntity<List<Reserva>> getReservasPorDataEntrada(@PathVariable String dataEntrada) {
        LocalDate data = LocalDate.parse(dataEntrada); // Converte a string para LocalDate
        List<Reserva> reservas = reservaService.getByDataEntrada(data);
        return ResponseEntity.ok(reservas);
    }

    // Endpoint para cadastrar uma nova reserva
    @PostMapping
    public ResponseEntity<Reserva> cadastrarReserva(@RequestBody Reserva reserva) {
        Reserva novaReserva = reservaService.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
    }

    // Endpoint para atualizar uma reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        Optional<Reserva> reservaExistente = reservaService.getById(id);
        if (reservaExistente.isPresent()) {
            reservaAtualizada.setId(id); // Garante que o ID da reserva atualizada seja o mesmo da reserva existente
            Reserva reservaAtualizadaDb = reservaService.save(reservaAtualizada);
            return ResponseEntity.ok(reservaAtualizadaDb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Outros métodos podem ser adicionados conforme necessário para operações específicas
}
