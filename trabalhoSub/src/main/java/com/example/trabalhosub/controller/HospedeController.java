package com.example.trabalhosub.controller;

import com.example.trabalhosub.model.Hospede;
import com.example.trabalhosub.service.HospedeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospede")
public class HospedeController {

    private final HospedeService hospedeService;

    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @GetMapping
    public ResponseEntity<List<Hospede>> getAll() {
        List<Hospede> hospedes = hospedeService.getAll();
        return ResponseEntity.ok(hospedes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospede> getById(@PathVariable int id) {
        Optional<Hospede> hospede = hospedeService.getById(id);
        return hospede.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Hospede> save(@RequestBody Hospede hospede) {
        Hospede savedHospede = hospedeService.save(hospede);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHospede);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospede> update(@PathVariable int id, @RequestBody Hospede hospede) {
        Optional<Hospede> existingHospede = hospedeService.getById(id);
        if (existingHospede.isPresent()) {
            hospede.setId(id); // Garante que o ID no corpo da requisição seja respeitado
            Hospede updatedHospede = hospedeService.save(hospede);
            return ResponseEntity.ok(updatedHospede);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<Hospede> existingHospede = hospedeService.getById(id);
        if (existingHospede.isPresent()) {
            hospedeService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
