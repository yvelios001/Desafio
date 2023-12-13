package com.example.caminhao.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Motorista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int entregaNoMes;
	private LocalDate ultimaData;
	@JsonIgnore
	@OneToMany(mappedBy = "motorista", cascade = CascadeType.ALL)
	private List<Entrega> entregas = new ArrayList<>();

	public Motorista() {
		this.entregas = new ArrayList<>();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Entrega> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<Entrega> entregas) {
		this.entregas = entregas;
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
