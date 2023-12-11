package com.example.caminhao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.caminhao.entidades.Entrega;
@Repository
public interface EntregaRepository extends JpaRepository<Entrega,Long> {

	//long countByMotoristaIdAndLocalAndDataEntregaYearMonth(Long motoristaId, LocalEntrega local, YearMonth yearMonth);
}
