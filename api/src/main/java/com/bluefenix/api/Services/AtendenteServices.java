package com.bluefenix.api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Atendente;
import com.bluefenix.api.Repositories.AtendenteRepository;

import jakarta.transaction.Transactional;

@Service
public class AtendenteServices {

    @Autowired
    private AtendenteRepository atendenteRepositorio;
    
    @Transactional
    public Atendente criarConta(Atendente dadosRecebidosDoAtendente) {
        dadosRecebidosDoAtendente.setIdAtendente(null);

        // Salvar as informações do Atendente na table
        this.atendenteRepositorio.save(dadosRecebidosDoAtendente);

        return dadosRecebidosDoAtendente;
    }
}