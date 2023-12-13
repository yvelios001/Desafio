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

		verificarLimiteEntregasCaminhaoMotorista(entrega);

		validarRegrasDeNegocio(entrega);

		atualizarCaminhaoMotorista(entrega);

		entrega.getCaminhao().getEntregas().add(entrega);
		entrega.getMotorista().getEntregas().add(entrega);

		caminhaorepository.save(entrega.getCaminhao());
		motoristarepository.save(entrega.getMotorista());

		entregarepository.save(entrega);

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

	public void verificarLimiteEntregasCaminhaoMotorista(Entrega entrega) {

		Long motoristaId = entrega.getMotorista().getId();
		Entrega.LocalEntrega localDesejado = Entrega.LocalEntrega.NORDESTE;

		long countEntregas = entregarepository.countEntregasByMotoristaIdAndLocal(motoristaId, localDesejado);
		System.out.println("Total de entregas para o NORDESTE: " + countEntregas);

		if (countEntregas >= 1) {
			throw new RuntimeException("Limite de Viagens Excedido pelo Motorista ao Nordeste");

		}

		long entregasDoCaminhaoNoMes = entregarepository.countEntregasByCaminhaoIdAndAnoAndMes(
				entrega.getCaminhao().getId(), entrega.getDataEntrega().getYear(),
				entrega.getDataEntrega().getMonthValue());

		long entregasDoMotoristaNoMes = entregarepository.countEntregasByMotoristaIdAndAnoAndMes(
				entrega.getMotorista().getId(), entrega.getDataEntrega().getYear(),
				entrega.getDataEntrega().getMonthValue());

				System.out.println("Entregas do Caminhão no Mês: " + entregasDoCaminhaoNoMes);
				System.out.println("Entregas do Motorista no Mês: " + entregasDoMotoristaNoMes);

		if (entregasDoMotoristaNoMes >= 2) {
			throw new RuntimeException("Limite de entregas para o Motorista atingido neste mês");
		}

		if (entregasDoCaminhaoNoMes >= 4) {
			throw new RuntimeException("Limite de entregas para o Caminhao atingido neste mês");

		}

	}

	private void validarRegrasDeNegocio(Entrega entrega) {

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
