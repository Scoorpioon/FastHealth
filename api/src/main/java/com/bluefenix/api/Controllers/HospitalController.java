package com.bluefenix.api.Controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bluefenix.api.Models.Hospital;
import com.bluefenix.api.Services.HospitalServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    private HospitalServices servicosHospital;

    @PostMapping("/criar")
    public ResponseEntity<?> cadastrarHospital(@RequestBody Hospital dados) {
        Hospital hospitalCadastrado = this.servicosHospital.cadastrarHospital(dados);

        if(hospitalCadastrado != null) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dados.getIdHospital()).toUri();

            return ResponseEntity.created(uri).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("hospital_ja_cadastrado");
        }
    }
}