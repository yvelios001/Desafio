package com.example.caminhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.caminhao.entidades.Motorista;
import com.example.caminhao.repository.MotoristaRepository;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {
	
	@Autowired
	private MotoristaRepository motoristaRepository;
	
	@PostMapping
	 public ResponseEntity<Motorista>CadastrarMotorista(@RequestBody Motorista motorista) {
	
		Motorista motoristaSalvo = motoristaRepository.save(motorista);
	        
		return new ResponseEntity<>(motoristaSalvo, HttpStatus.CREATED);
		 
	      
}
	 }
