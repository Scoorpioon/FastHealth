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
import com.bluefenix.api.Models.DTOs.MetodosWS.BuscarFilaPorDataRequest;
import com.bluefenix.api.Models.DTOs.MetodosWS.RemoverConsultaRequest;
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

    @MessageMapping("/retornarFilaPorData")
    @SendTo("/filaWS")
    public Fila retornarFilaAtualizada(BuscarFilaPorDataRequest requisicao) {
        System.out.println(String.format("Requisição de fila [ID: undefined] realizada"));

        Fila filaRequisitada = servicoFila.encontrarFilaPorData(requisicao.getDataFila());

        if(filaRequisitada != null) {
            return filaRequisitada;
        } else {
            return new Fila();
        }
    }

    @MessageMapping("/removerConsulta")
    @SendTo("/filaWS")
    public Fila removerConsulta(RemoverConsultaRequest requisicao) {
        System.out.println("Remoção de consulta requisitada");

        System.out.println("Fila requisitada: " + requisicao.getIdFila());
        System.out.println("Consulta requisitada: " + requisicao.getIdConsulta());

        Long filaId = requisicao.getIdFila();
        Long consultaId = requisicao.getIdConsulta();

        return servicoFila.removerPacienteDaFila(consultaId, filaId);
    }
}