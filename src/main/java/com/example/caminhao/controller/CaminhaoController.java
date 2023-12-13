package com.example.caminhao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.caminhao.entidades.Caminhao;
import com.example.caminhao.repository.CaminhaoRepository;

@RestController
@RequestMapping("/caminhoes")
public class CaminhaoController {

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    @PostMapping
    public ResponseEntity<Caminhao> criarCaminhao(@RequestBody Caminhao caminhao) {
        Caminhao caminhaoSalvo = caminhaoRepository.save(caminhao);
        return new ResponseEntity<>(caminhaoSalvo, HttpStatus.CREATED);
    }
    
    @GetMapping("/pegar")
    public ResponseEntity<List<Caminhao>> getAllCaminhoes() {
        List<Caminhao> caminhoes = caminhaoRepository.findAll();
        if (caminhoes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(caminhoes, HttpStatus.OK);
    }
    
    
    
    
    
    
}