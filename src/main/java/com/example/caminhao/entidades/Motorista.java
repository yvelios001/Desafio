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
	private Long id_motorista;
	private String nome;
	private int entregaNoMes;
	private LocalDate ultimaData;
	
	public Long getId_motorista() {
		return id_motorista;
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
	public void setId_mototista(Long id_mototista) {
		this.id_motorista = id_mototista;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
}
