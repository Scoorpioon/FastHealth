package com.bluefenix.api.Controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bluefenix.api.Models.Atendente;
import com.bluefenix.api.Services.AtendenteServices;

@RestController
@RequestMapping("/atendente")
public class AtendenteController {
    @Autowired
    private AtendenteServices atendenteServicos;
    
    @PostMapping("/criar")
    @Validated
    public ResponseEntity<Void> criarAtendente(@RequestBody Atendente informacoesRecebidasDoAtendente) {
        this.atendenteServicos.criarConta(informacoesRecebidasDoAtendente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(informacoesRecebidasDoAtendente.getIdAtendente()).toUri();

        return ResponseEntity.created(uri).build();
    }
}