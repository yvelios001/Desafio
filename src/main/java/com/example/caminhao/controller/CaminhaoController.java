package com.example.caminhao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Caminhao> atualizarCaminhao(@PathVariable Long id, @RequestBody Caminhao novoCaminhao) {
        // Verifica se o caminhão com o ID especificado existe
        if (!caminhaoRepository.existsById(id)) {
          
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Obtém o caminhão existente do banco de dados
        Caminhao caminhaoExistente = caminhaoRepository.findById(id).get();

        // Atualiza os campos do caminhão existente com os dados do novo caminhão
        caminhaoExistente.setPlaca(novoCaminhao.getPlaca());
        caminhaoExistente.setEntregasFeitasNoMes(novoCaminhao.getEntregasFeitasNoMes());
        caminhaoExistente.setUltimaEntregaData(novoCaminhao.getUltimaEntregaData());

        // Salva o caminhão atualizado no banco de dados
        Caminhao caminhaoAtualizado = caminhaoRepository.save(caminhaoExistente);

        return new ResponseEntity<>(caminhaoAtualizado, HttpStatus.OK);
    }
    
    
    
}