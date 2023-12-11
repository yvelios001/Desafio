package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.caminhao.entidades.Caminhao;

@Repository
public interface Caminhao_Repository extends JpaRepository<Caminhao, Long> {
	
}
  