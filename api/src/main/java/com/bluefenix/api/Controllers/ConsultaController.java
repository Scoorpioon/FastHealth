package com.bluefenix.api.Controllers;

import java.net.URI;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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



@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaServices servicosConsulta;

    @PostMapping("/criar")
    public ResponseEntity<Void> criarConsulta(@RequestBody Consulta dadosRecebidosDeLaConsuelta) {
        this.servicosConsulta.criarConsulta(dadosRecebidosDeLaConsuelta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dadosRecebidosDeLaConsuelta.getIdConsulta()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/buscarConsultas")
    public ResponseEntity<List<ConsultaDTO>> listarConsultas() {
        List<ConsultaDTO> consultasEncontradas = this.servicosConsulta.listarConsultas();
        return ResponseEntity.ok(consultasEncontradas);
    }
}