package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.caminhao.entidades.Motorista;

@Repository
public interface Motorista_Repository extends JpaRepository<Motorista,Long> {
	

}
