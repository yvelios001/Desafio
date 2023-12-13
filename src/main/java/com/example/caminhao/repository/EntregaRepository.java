package com.example.caminhao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.caminhao.entidades.Entrega;
@Repository
public interface EntregaRepository extends JpaRepository<Entrega,Long> {


	@Query("SELECT COUNT(e) FROM Entrega e WHERE e.caminhao.id = :caminhaoId AND YEAR(e.dataEntrega) = :ano AND MONTH(e.dataEntrega) = :mes")
	long countEntregasByCaminhaoIdAndAnoAndMes(@Param("caminhaoId") Long caminhaoId, @Param("ano") int ano, @Param("mes") int mes);


	@Query("SELECT COUNT(e) FROM Entrega e WHERE e.motorista.id = :motoristaId AND YEAR(e.dataEntrega) = :ano AND MONTH(e.dataEntrega) = :mes")
	long countEntregasByMotoristaIdAndAnoAndMes(@Param("motoristaId") Long motoristaId, @Param("ano") int ano, @Param("mes") int mes);


	@Query("SELECT COUNT(e) FROM Entrega e WHERE e.motorista.id = :motoristaId AND e.local = :local")
	long countEntregasByMotoristaIdAndLocal(@Param("motoristaId") Long motoristaId, @Param("local") Entrega.LocalEntrega local);


	



}






