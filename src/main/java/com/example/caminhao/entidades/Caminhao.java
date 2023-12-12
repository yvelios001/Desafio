package com.example.caminhao.entidades;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Caminhao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCaminhao;
	private String placa;
	private int entregasFeitasNoMes;
	private LocalDate ultimaEntregaData;
	
	
	
	
	public Long getIdCaminhao() {
		return idCaminhao;
	}
	public void setIdCaminhao(Long idCaminhao) {
		this.idCaminhao = idCaminhao;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getEntregasFeitasNoMes() {
		return entregasFeitasNoMes;
	}
	public void setEntregasFeitasNoMes(int entregasFeitasNoMes) {
		this.entregasFeitasNoMes = entregasFeitasNoMes;
	}
	public LocalDate getUltimaEntregaData() {
		return ultimaEntregaData;
	}
	public void setUltimaEntregaData(LocalDate ultimaEntregaData) {
		this.ultimaEntregaData = ultimaEntregaData;
	}

	
	
	
}
