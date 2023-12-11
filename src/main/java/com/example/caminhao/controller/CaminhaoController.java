package com.example.caminhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        // Validações podem ser adicionadas aqui, se necessário

        // Salvar o caminhão no banco de dados
        Caminhao caminhaoSalvo = caminhaoRepository.save(caminhao);

        // Retornar o caminhão salvo e um status 201 (CREATED)
        return new ResponseEntity<>(caminhaoSalvo, HttpStatus.CREATED);
    }
}