package com.example.trabalhosub.service;

import com.example.trabalhosub.model.Reserva;
import com.example.trabalhosub.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> getAll() {
        return reservaRepository.findAll();
    }

    public Reserva save(Reserva reserva) {
        // Implementar lógica adicional antes de salvar, se necessário
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> getById(Long id) {
        return reservaRepository.findById(Math.toIntExact(id));
    }

    public List<Reserva> getByHospedeId(Long hospedeId) {
        return reservaRepository.findByHospedeId(hospedeId);
    }

    public List<Reserva> getByQuartoId(Long quartoId) {
        return reservaRepository.findByQuartoId(quartoId);
    }

    public List<Reserva> getByDataEntrada(LocalDate dataEntrada) {
        return reservaRepository.findByDataEntrada(dataEntrada);
    }

    public List<Reserva> getAllReservasOcupadas() {
        return reservaRepository.findByStatus("ocupada");
    }

    public Reserva update(Reserva reserva) {
        // Verificar se a reserva existe antes de atualizar
        if (reserva.getId() == null || !reservaRepository.existsById(reserva.getId())) {
            throw new IllegalArgumentException("Reserva não encontrada para atualização");
        }
        return reservaRepository.save(reserva);
    }

    public void delete(Long id) {
        reservaRepository.deleteById(Math.toIntExact(id));
    }
}
