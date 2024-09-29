package com.bluefenix.api.Controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import com.bluefenix.api.Models.Paciente;
import com.bluefenix.api.Services.PacienteServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/pacientes")
@Validated
public class PacienteController {
    @Autowired
    private PacienteServices pacienteServicos;

    @GetMapping("/{cpf}")
    public ResponseEntity<Paciente> findByCpf(@PathVariable String cpf) {
        Paciente pacienteEncontrado = this.pacienteServicos.findByCPF(cpf);

        return ResponseEntity.ok().body(pacienteEncontrado);
    }

    @PostMapping("/criar")
    @Validated
    public ResponseEntity<Void> criarPaciente(@RequestBody Paciente informacoesRecebidasDoPaciente) {
        this.pacienteServicos.criarConta(informacoesRecebidasDoPaciente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(informacoesRecebidasDoPaciente.getIdPaciente()).toUri();

        return ResponseEntity.created(uri).build();
    }
}