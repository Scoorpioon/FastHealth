package com.bluefenix.api.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Consulta;
import com.bluefenix.api.Models.Fila;
import com.bluefenix.api.Models.DTOs.ConsultaDTO;
import com.bluefenix.api.Repositories.ConsultaRepository;

@Service
public class ConsultaServices {

    @Autowired
    private ConsultaRepository repositorioConsulta;

    @Autowired
    private FilaServices filaServices;

    public Consulta criarConsulta(Consulta dadosRecebidos) {
        dadosRecebidos.setIdConsulta(null);

        System.out.println("Criação de fila requisitada. Data da consulta: " + dadosRecebidos.getDataConsulta());

        Fila filaEncontrada = this.filaServices.encontrarFilaPorData(dadosRecebidos.getDataConsulta().toLocalDate());
        if(filaEncontrada == null) {
            filaEncontrada = this.filaServices.cadastrarFila(
                new Fila(dadosRecebidos.getDataConsulta().toLocalDate())
            );
        }

        dadosRecebidos.setFila(filaEncontrada);

        System.out.println("ID da fila cadastrada na consulta: " + dadosRecebidos.getFila().getIdFila());

        this.repositorioConsulta.save(dadosRecebidos);

        return dadosRecebidos;
    }

    public ConsultaDTO converterParaDTO(Consulta consulta) {
        return new ConsultaDTO(
            consulta.getIdConsulta(),
            consulta.getDataConsulta(),
            consulta.getTipoConsulta(),
            consulta.getPaciente().getIdPaciente(),
            consulta.getFila().getIdFila()
        );
    }

    public List<ConsultaDTO> listarConsultas() {
        List<Consulta> consultasEncontradas = repositorioConsulta.findAll();

        return consultasEncontradas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}