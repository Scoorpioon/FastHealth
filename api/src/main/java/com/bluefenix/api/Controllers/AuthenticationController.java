/* package com.bluefenix.api.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluefenix.api.Models.Atendente;
import com.bluefenix.api.Models.domain.AtAuthenticationDTO;
import com.bluefenix.api.Models.domain.AtRegisterDTO;
import com.bluefenix.api.Models.domain.PaAuthenticationDTO;
import com.bluefenix.api.Repositories.AtendenteRepository;
import com.bluefenix.api.Repositories.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid; */

// Estou estudando sobre autenticação no Spring só agora. Eu acho que não necessariamente precisamos criar um controller somente para fazer autenticação, podemos inserir a autenticação em cada controller de usuário, como no de paciente e no de atendednte. Mas, por enquanto, vamo deixar assim mesmo. É bom que até organiza e deixa mais legível para eu ir relendo o código e estudando mais profundamente o conteúdo.

/* @RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AtendenteRepository atendenteRepository;
    
    @PostMapping("/atendente/cadastro")
    public ResponseEntity register(@RequestBody @Valid AtRegisterDTO dados) {
        if(this.atendenteRepository.findByCpf(dados.cpf()) != null) return ResponseEntity.badRequest().build();

        String senhaEncriptada = new BCryptPasswordEncoder().encode(dados.senha());
        Atendente atendenteCriado = new Atendente(dados.cpf(), dados.nome(), dados.email(), senhaEncriptada, dados.roles());

        this.atendenteRepository.save(atendenteCriado);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/atendente/login")
    public ResponseEntity loginAtendente(@RequestBody @Valid AtAuthenticationDTO dados) {
        var nomeESenha = new UsernamePasswordAuthenticationToken(dados.cpf(), dados.senha());
        System.out.println(nomeESenha.toString()); */
        /* var autenticacao = this.authenticationManager.authenticate(nomeESenha); */

/*         return ResponseEntity.ok().build();
    }

    @PostMapping("/paciente/login")
    public ResponseEntity loginPaciente(@RequestBody @Valid PaAuthenticationDTO dados) {
        var cpfESenha = new UsernamePasswordAuthenticationToken(dados.cpf(), dados.senha());
        var autenticacao = this.authenticationManager.authenticate(cpfESenha);

        return ResponseEntity.ok().build();
    }
} */