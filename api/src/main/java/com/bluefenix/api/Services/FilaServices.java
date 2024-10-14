package com.bluefenix.api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Fila;
import com.bluefenix.api.Repositories.FilaRepository;

import java.util.List;

import java.time.LocalDate;

import jakarta.transaction.Transactional;

@Service
public class FilaServices {
    
    @Autowired
    private FilaRepository filaRepositorio;

    @Transactional
    public Fila cadastrarFila(Fila dadosRecebidos) {
        // Por enquanto vai ser só isso memo
        dadosRecebidos.setIdFila(null);

        this.filaRepositorio.save(dadosRecebidos);

        return dadosRecebidos;
    }

    @Transactional
    public List<Fila> findAll() {
        return this.filaRepositorio.findAll();

    }

    @Transactional
    public Fila encontrarFilaPorData(LocalDate data) {
        Fila filaEncontrada = this.filaRepositorio.findByDataFila(data);

        return filaEncontrada;
    }

/*     @Transactional
    public Consulta addConsultaToFila(Date data) {
        

    }
 */
    /* private Fila alterarPacienteAtual(long id) {

    } */
}