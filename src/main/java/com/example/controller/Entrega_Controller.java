package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.caminhao.entidades.Entrega;
import com.example.service.EntregaService;

@RestController
@RequestMapping("/api/entregas")
public class Entrega_Controller {
	
	
	@Autowired
    private EntregaService entregaService;

	@PostMapping("/realizar")
    public ResponseEntity<String> realizarEntrega(@RequestBody Entrega entrega) {
        try {
            entregaService.realizarEntrega(entrega);
            return new ResponseEntity<>("Entrega realizada com sucesso", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	

		
	} 
	
	

	
	