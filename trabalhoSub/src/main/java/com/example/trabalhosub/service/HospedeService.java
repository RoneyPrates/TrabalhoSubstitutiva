package com.example.trabalhosub.service;

import com.example.trabalhosub.model.Hospede;
import com.example.trabalhosub.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospedeService {

    private final HospedeRepository hospedeRepository;

    @Autowired
    public HospedeService(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public List<Hospede> getAll() {
        return hospedeRepository.findAll();
    }

    public Optional<Hospede> getById(int id) {
        return hospedeRepository.findById(id);
    }

    public Hospede save(Hospede hospede) {
        return hospedeRepository.save(hospede);
    }

    public void delete(int id) {
        hospedeRepository.deleteById(id);
    }
}
