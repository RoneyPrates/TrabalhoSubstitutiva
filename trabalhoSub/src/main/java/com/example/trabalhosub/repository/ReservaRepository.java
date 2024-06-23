package com.example.trabalhosub.repository;

import com.example.trabalhosub.model.Hospede;
import com.example.trabalhosub.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByHospedeId(Long hospedeId);

    List<Reserva> findByQuartoId(Long quartoId);

    List<Reserva> findByDataEntrada(LocalDate dataEntrada);

    List<Reserva> findByStatus(String ocupada);
}
