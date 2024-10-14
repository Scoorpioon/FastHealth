package com.bluefenix.api.Controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bluefenix.api.Models.Fila;
import com.bluefenix.api.Services.FilaServices;
import com.bluefenix.api.Services.PacienteServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/fila")
public class FilaController {
    
    @Autowired
    private FilaServices filaServicos;

    @Autowired
    private PacienteServices pacienteServicos;

    @PostMapping("/criar")
    @Validated
    private ResponseEntity criarFila(@RequestBody Fila informacoesEnviadasPelaPorraDoHeader) {
        this.filaServicos.cadastrarFila(informacoesEnviadasPelaPorraDoHeader);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(informacoesEnviadasPelaPorraDoHeader.getIdFila()).toUri();

        return ResponseEntity.created(uri).build();
    }

    // Requisição genérica provisória para visualizarmos todas as filas. Deve deixar de existir posteriormente, pois isso ai vai sobrecarregar a API se entrar em ambiente de produção e tivermos trocentas filas
    @GetMapping("/todasAsFilas")
    public ResponseEntity<List<Fila>> requisitarTodasAsFilas() {
        List<Fila> filas = this.filaServicos.findAll();

        return ResponseEntity.ok(filas);
    }

    @GetMapping("/encontrar/{dataDaFila}")

    public ResponseEntity<Fila> encontrarFilaPorData(@PathVariable LocalDate dataDaFila) {
        Fila filaEncontrada = filaServicos.encontrarFilaPorData(dataDaFila);

        if(filaEncontrada != null) {
            return ResponseEntity.ok(filaEncontrada);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    
    
    
}