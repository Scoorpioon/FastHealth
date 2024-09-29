package com.bluefenix.api.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Paciente;
import com.bluefenix.api.Repositories.PacienteRepository;

@Service
public class PacienteServices {
    @Autowired
    private PacienteRepository pacienteRepositorio;

    public Paciente findById(Long id) {
        Optional<Paciente> pacienteBuscado = this.pacienteRepositorio.findById(id);

        return pacienteBuscado.orElseThrow(() -> new RuntimeException(String.format("Não foi possível achar o paciente de id %s", id)));
    }

    public Paciente findByCPF(String cpf) {
        Paciente pacienteBuscado = this.pacienteRepositorio.findByCPF(cpf);
        System.out.println("! Busca realizada por CPF: " + pacienteBuscado + " !");

        return pacienteBuscado;
    }
}