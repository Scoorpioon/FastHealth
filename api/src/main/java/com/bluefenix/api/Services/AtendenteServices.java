package com.bluefenix.api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Atendente;
import com.bluefenix.api.Models.Login;
import com.bluefenix.api.Repositories.AtendenteRepository;
import com.bluefenix.api.Repositories.LoginRepository;

import jakarta.transaction.Transactional;

@Service
public class AtendenteServices {

    @Autowired
    private AtendenteRepository atendenteRepositorio;

    @Autowired
    private LoginRepository loginRepositorio;
    
    @Transactional
    public Atendente criarConta(Atendente dadosRecebidosDoAtendente) {
        dadosRecebidosDoAtendente.setIdAtendente(null);

        /* Login infosDeLogin = new Login(dadosRecebidosDoAtendente.getEmail(), dadosRecebidosDoAtendente.getSenha(), dadosRecebidosDoAtendente.get); */

        // this.loginRepositorio.save();
        this.atendenteRepositorio.save(dadosRecebidosDoAtendente);

        return dadosRecebidosDoAtendente;
    }
}