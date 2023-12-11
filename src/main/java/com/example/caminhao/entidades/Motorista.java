package com.example.caminhao.entidades;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Motorista {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int entregaNoMes;
	private LocalDate ultimaData;
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public LocalDate getUltimaData() {
		return ultimaData;
	}

	public void setUltimaData(LocalDate ultimaData) {
		this.ultimaData = ultimaData;
	}

	public int getEntregaNoMes() {
		return entregaNoMes;
	}
	public void setEntregaNoMes(int entregaNoMes) {
		this.entregaNoMes = entregaNoMes;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
}
