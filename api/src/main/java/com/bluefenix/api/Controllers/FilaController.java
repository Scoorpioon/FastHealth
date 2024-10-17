package com.bluefenix.api.Controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bluefenix.api.Models.Fila;
import com.bluefenix.api.Services.FilaServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import java.time.LocalDate;

@Controller
@RequestMapping("/api/fila")
public class FilaController {
    
    @Autowired
    private FilaServices servicoFila;

    @PostMapping("/criar")
    @Validated
    private ResponseEntity<Fila> criarFila(@RequestBody Fila informacoesEnviadasPelaPorraDoHeader) {
        this.servicoFila.cadastrarFila(informacoesEnviadasPelaPorraDoHeader);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(informacoesEnviadasPelaPorraDoHeader.getIdFila()).toUri();

        return ResponseEntity.created(uri).build();
    }

    // Requisição genérica provisória para visualizarmos todas as filas. Deve deixar de existir posteriormente, pois isso ai vai sobrecarregar a API se entrar em ambiente de produção e tivermos trocentas filas
    @GetMapping("/todasAsFilas")
    public ResponseEntity<List<Fila>> requisitarTodasAsFilas() {
        List<Fila> filas = this.servicoFila.findAll();

        return ResponseEntity.ok(filas);
    }

    @GetMapping("/encontrar/{dataDaFila}")
    public ResponseEntity<Fila> encontrarFilaPorData(@PathVariable LocalDate dataDaFila) {
        Fila filaEncontrada = servicoFila.encontrarFilaPorData(dataDaFila);

        if(filaEncontrada != null) {
            return ResponseEntity.ok(filaEncontrada);
        } else {
            return ResponseEntity.notFound().build(); // Por enquanto vamos apenas retornar o erro 404
        }

    }

    @MessageMapping("/filaws")
    @SendTo("/filaws")
    public Fila removerConsulta(Long idConsulta, Long idFila) {
        return servicoFila.removerPacienteDaFila(idConsulta, idFila);
    }
}