/* package com.bluefenix.api.Security.Autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Atendente;
import com.bluefenix.api.Models.Paciente;
import com.bluefenix.api.Repositories.AtendenteRepository;
import com.bluefenix.api.Repositories.PacienteRepository;

@Service
public class ServicosUsuario implements UserDetailsService {
    @Autowired
    private PacienteRepository repositorioPaciente;

    @Autowired
    private AtendenteRepository repositorioAtendente;

    private UserDetails findByPacienteEmail(String email) {
        return repositorioPaciente.findByEmail(email);
    }

    private UserDetails findByAtendenteEmail(String email) {
        return repositorioAtendente.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByName(String email) throws UsernameNotFoundException {
        UserDetails paciente = findByPacienteEmail(email);
        if (paciente != null) {
             return new Paciente();
        }

        UserDetails atendente = findByAtendenteEmail(email);

        if (atendente != null) {
             return new Atendente();
        } else{
               throw new UsernameNotFoundException("No user Found");
        }

    }

} */