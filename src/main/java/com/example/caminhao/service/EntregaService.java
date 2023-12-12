package com.example.caminhao.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.caminhao.entidades.Caminhao;
import com.example.caminhao.entidades.Entrega;
import com.example.caminhao.entidades.Entrega.LocalEntrega;
import com.example.caminhao.entidades.Entrega.TipoEntrega;
import com.example.caminhao.entidades.Motorista;
import com.example.caminhao.repository.CaminhaoRepository;
import com.example.caminhao.repository.EntregaRepository;
import com.example.caminhao.repository.MotoristaRepository;

@Service
public class EntregaService {

	@Autowired
	private CaminhaoRepository caminhaorepository;

	@Autowired
	private MotoristaRepository motoristarepository;

	@Autowired
	private EntregaRepository entregarepository;

	public void realizarEntrega(Entrega entrega) {

		atualizarCaminhaoMotorista(entrega);

		validarRegrasDeNegocio(entrega);

		entregarepository.save(entrega);

		Motorista motorista = entrega.getMotorista();
		Caminhao caminhao = entrega.getCaminhao();

		motoristarepository.save(motorista);
		caminhaorepository.save(caminhao);

	}

	private void atualizarCaminhaoMotorista(Entrega entrega) {

		Caminhao caminhao = entrega.getCaminhao();
		caminhao.setUltimaEntregaData(entrega.getDataEntrega());
		caminhao.setEntregasFeitasNoMes(caminhao.getEntregasFeitasNoMes() + 1);
		caminhaorepository.save(caminhao);

		Motorista motorista = entrega.getMotorista();
		motorista.setUltimaData(entrega.getDataEntrega());
		motorista.setEntregaNoMes(motorista.getEntregaNoMes() + 1);
		motoristarepository.save(motorista);

	}

	private void validarRegrasDeNegocio(Entrega entrega) {
		if (entrega.getCaminhao().getUltimaEntregaData() != null
				&& entrega.getCaminhao().getUltimaEntregaData().getMonthValue() == LocalDate.now().getMonthValue()) {
			if (entrega.getCaminhao().getEntregasFeitasNoMes() >= 4) {
				throw new RuntimeException("Limite de entregas para o caminhão atingido neste mês");
			}
		}

		if (entrega.getValor().compareTo(new BigDecimal("30000")) > 0) {
			entrega.seteValiosa(true);
			entrega.setTemSeguro(true);
		}

		if (TipoEntrega.COMBUSTIVEL.equals(entrega.getTipo())) {
			entrega.setePerigosa(true);
		}

		if (entrega.getMotorista().getUltimaData() != null
				&& entrega.getMotorista().getUltimaData().getMonthValue() == LocalDate.now().getMonthValue()) {
			if (entrega.getMotorista().getEntregaNoMes() >= 2) {
				throw new RuntimeException("Limite de entregas para o Motorista atingido neste mês");
			}
		}
		if (LocalEntrega.AMAZONAS.equals(entrega.getLocal())) {
			entrega.setValor(entrega.getValor().multiply(new BigDecimal("1.3")));
		} else if (LocalEntrega.NORDESTE.equals(entrega.getLocal())) {
			entrega.setValor(entrega.getValor().multiply(new BigDecimal("1.2")));
		} else if (LocalEntrega.ARGENTINA.equals(entrega.getLocal())) {
			entrega.setValor(entrega.getValor().multiply(new BigDecimal("1.4")));
		}
	}

}
