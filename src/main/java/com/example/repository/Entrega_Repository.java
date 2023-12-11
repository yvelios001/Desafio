package com.example.repository;

import java.time.YearMonth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.caminhao.entidades.Entrega;
import com.example.caminhao.entidades.Entrega.LocalEntrega;
@Repository
public interface Entrega_Repository extends JpaRepository<Entrega,Long> {

	long countByMotoristaIdAndLocalAndDataEntregaYearMonth(Long motoristaId, LocalEntrega local, YearMonth yearMonth);
}
