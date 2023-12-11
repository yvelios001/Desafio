package com.example.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.caminhao.entidades.Caminhao;
import com.example.caminhao.entidades.Entrega;
import com.example.caminhao.entidades.Entrega.LocalEntrega;
import com.example.caminhao.entidades.Motorista;
import com.example.repository.Caminhao_Repository;
import com.example.repository.Entrega_Repository;
import com.example.repository.Motorista_Repository;

@Service
public class EntregaService {
	
	
	@Autowired
	private Caminhao_Repository caminhao_repository;
	
	@Autowired
	private Motorista_Repository motorista_repository;
	
	@Autowired
	private Entrega_Repository entrega_repository;
	
	
	public void realizarEntrega(Entrega entrega) {
    validarRegrasDeNegocio(entrega);
    
    entrega_repository.save(entrega);
    
    atualizarCaminhaoMotorista(entrega);
	
	}
	


	private void atualizarCaminhaoMotorista(Entrega entrega) {
		 // Atualizar informações do Caminhão
	    Caminhao caminhao = entrega.getCaminhao();
	    caminhao.setUltimaEntregaData(entrega.getDataEntrega());
	    caminhao.setEntregasFeitasNoMes(caminhao.getEntregasFeitasNoMes() + 1);
	    caminhao_repository.save(caminhao);

	    // Atualizar informações do Motorista
	    Motorista motorista = entrega.getMotorista();
	    motorista.setUltimaData(entrega.getDataEntrega());
	    motorista.setEntregaNoMes(motorista.getEntregaNoMes() + 1);
	    motorista_repository.save(motorista);
		
	}



	private void validarRegrasDeNegocio(Entrega entrega) {
		if (entrega.getCaminhao().getUltimaEntregaData() != null && entrega.getCaminhao().getUltimaEntregaData().getMonthValue() == LocalDate.now().getMonthValue()) {
            if (entrega.getCaminhao().getEntregasFeitasNoMes() >= 4) {
                throw new RuntimeException("Limite de entregas para o caminhão atingido neste mês");
            }
        }
		
		if (entrega.getValor().compareTo(new BigDecimal("30000")) > 0) {
            entrega.seteValiosa(true);
        }
		
		if ("ELETRONICOS".equals(entrega.getTipo())) {
			if(entrega.iseValiosa()) {
				entrega.setTemSeguro(true);
			}
			 }

		if ("COMBUSTIVEL".equals(entrega.getTipo())) {
			entrega.setePerigosa(true);
		}
	

	
	if (entrega.getMotorista().getUltimaData() != null && entrega.getMotorista().getUltimaData().getMonthValue()==LocalDate.now().getMonthValue()) {
		  if (entrega.getMotorista().getEntregaNoMes() >= 2) {
              throw new RuntimeException("Limite de entregas para o Motorista atingido neste mês");
		  }
	}	
	
	if ("AMAZONAS".equals(entrega.getLocal())) {
		entrega.setValor(entrega.getValor().multiply(new BigDecimal("1.3")));
	
		if("NORDESTE".equals(entrega.getLocal())) {
			entrega.setValor(entrega.getValor().multiply(new BigDecimal("1.2")));
		}
		
		if("ARGENTINA".equals(entrega.getLocal())) {
			entrega.setValor(entrega.getValor().multiply(new BigDecimal("1.4")));
		}
	}
	
		
	
	
	}
	
	
	public void verificarEntregaNordesteMesAtual(Long motoristaId) {
        LocalEntrega nordeste = LocalEntrega.NORDESTE;
        YearMonth yearMonth = YearMonth.now();

        long entregasNoNordesteNoMes = entrega_repository.countByMotoristaIdAndLocalAndDataEntregaYearMonth(
                motoristaId, nordeste, yearMonth);

        if (entregasNoNordesteNoMes > 0) {
            throw new RuntimeException("O motorista já fez uma entrega para o Nordeste neste mês");
        }
}


	
}


	
	
	




