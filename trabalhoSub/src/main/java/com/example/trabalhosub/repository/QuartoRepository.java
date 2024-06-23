package com.example.trabalhosub.repository;

import com.example.trabalhosub.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Integer> {

    List<Quarto> findByStatus(boolean status);

    List<Quarto> findByVistaMarAndStatus(boolean vistaMar, boolean status);

    List<Quarto> findByQuantidadeOcupantes(int quantidadeOcupantes);

    List<Quarto> findByStatusTrue();

    @Query("SELECT q FROM Quarto q WHERE q.status = true")
    List<Quarto> findQuartosDisponiveis();

}
