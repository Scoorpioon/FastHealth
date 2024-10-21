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
        } // A consulta já associa automaticamente à fila, e se a fila não existir, é criada aqui mesmo também. Por questões de segurança, eu fiz com que o código não permita mais inserção manual do ID de uma fila na consulta, belê?

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
    } // Eu não lembro pra quê eu fiz uma DTO de consulta, mas algum bom motivo teve kkkkk

    public List<ConsultaDTO> listarConsultas() {
        List<Consulta> consultasEncontradas = repositorioConsulta.findAll();

        return consultasEncontradas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}