package com.bluefenix.api.Controllers;

import java.net.URI;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bluefenix.api.Models.Consulta;
import com.bluefenix.api.Models.DTOs.ConsultaDTO;
import com.bluefenix.api.Services.ConsultaServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaServices servicosConsulta;

    @PostMapping("/criar")
    public ResponseEntity<?> criarConsulta(@RequestBody Consulta dadosRecebidosDeLaConsuelta) {
        if(dadosRecebidosDeLaConsuelta.getFila() != null) {
            return ResponseEntity.badRequest().body("A fila não pode ser inserida manualmente na criação da consulta.");
        }

        Consulta consultaCriada = this.servicosConsulta.criarConsulta(dadosRecebidosDeLaConsuelta);

        if(consultaCriada != null) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dadosRecebidosDeLaConsuelta.getIdConsulta()).toUri();
    
            return ResponseEntity.created(uri).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("consulta_existente");
        }
    }

    @GetMapping("/buscarConsultas")
    public ResponseEntity<List<ConsultaDTO>> listarConsultas() {
        List<ConsultaDTO> consultasEncontradas = this.servicosConsulta.listarConsultas();
        return ResponseEntity.ok(consultasEncontradas);
    }

    @GetMapping("/buscarConsultas/{data}")
    public ResponseEntity<List<Consulta>> buscarPorData(@PathVariable LocalDate data) {
        List<Consulta> consultasEncontradas = this.servicosConsulta.listarConsultasPorData(data);
        
        return ResponseEntity.ok(consultasEncontradas);
    }
}