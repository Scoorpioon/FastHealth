package com.bluefenix.api.Services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Paciente;
import com.bluefenix.api.Repositories.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class PacienteServices {
    @Autowired
    private PacienteRepository pacienteRepositorio;

    public Paciente findById(Long id) {
        Optional<Paciente> pacienteBuscado = this.pacienteRepositorio.findById(id);

        return pacienteBuscado.orElseThrow(() -> new RuntimeException(String.format("Não foi possível achar o paciente de id %s", id)));
    }

    public Paciente findByCPF(String cpf) {
        Paciente pacienteBuscado = this.pacienteRepositorio.findByCpf(cpf);
        System.out.println("! Busca realizada por CPF: " + pacienteBuscado + " !");

        return pacienteBuscado;
    }

    public List<Paciente> findAll() {
        System.out.println("ATENÇÃO ATENÇÃO: TODOS OS PACIENTES FORAM BUSCADOS!!!!!!!");

        return this.pacienteRepositorio.findAll();
    }

    @Transactional
    public Paciente criarConta(Paciente dadosRecebidosDoPaciente) {
        dadosRecebidosDoPaciente.setIdPaciente(null);

        // Salvar as informações do paciente na table
        this.pacienteRepositorio.save(dadosRecebidosDoPaciente);

        return dadosRecebidosDoPaciente;
    }
}