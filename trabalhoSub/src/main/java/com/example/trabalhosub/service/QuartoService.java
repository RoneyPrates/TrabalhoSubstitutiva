package com.example.trabalhosub.service;

import com.example.trabalhosub.model.Quarto;
import com.example.trabalhosub.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    @Autowired
    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    public List<Quarto> getAll() {
        return quartoRepository.findAll();
    }

    public Optional<Quarto> getById(int id) {
        return quartoRepository.findById((int) id);
    }

    public Quarto save(Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    public void delete(int id) {
        quartoRepository.deleteById((int) id);
    }

    public List<Quarto> findQuartosDisponiveis() {
        return quartoRepository.findQuartosDisponiveis();
    }

    public List<Quarto> findQuartosByQuantidadeOcupantes(int quantidadeOcupantes) {
        return quartoRepository.findByQuantidadeOcupantes(quantidadeOcupantes);
    }
}
