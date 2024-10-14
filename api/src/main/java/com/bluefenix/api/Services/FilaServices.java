package com.bluefenix.api.Services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Consulta;
import com.bluefenix.api.Models.Fila;
import com.bluefenix.api.Repositories.FilaRepository;

import java.util.List;

import jakarta.transaction.Transactional;

@Service
public class FilaServices {
    
    @Autowired
    private FilaRepository filaRepositorio;

    @Autowired
    private PacienteServices servicosPaciente;

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

/*     @Transactional
    public Consulta addConsultaToFila(Date data) {
        

    }
 */
    /* private Fila alterarPacienteAtual(long id) {

    } */
}