package com.bluefenix.api.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Consulta;
import com.bluefenix.api.Models.DTOs.ConsultaDTO;
import com.bluefenix.api.Repositories.ConsultaRepository;

@Service
public class ConsultaServices {

    @Autowired
    private ConsultaRepository repositorioConsulta;

    public Consulta criarConsulta(Consulta dadosRecebidos) {
        dadosRecebidos.setIdConsulta(null);

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