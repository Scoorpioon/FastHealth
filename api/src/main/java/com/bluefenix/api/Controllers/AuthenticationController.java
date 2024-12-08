package com.bluefenix.api.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluefenix.api.Models.Atendente;
import com.bluefenix.api.Models.Paciente;
import com.bluefenix.api.Models.Usuario;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.CredentialsDTO;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.RegistroAtendenteDTO;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.RegistroPacienteDTO;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.SessaoAtendenteDTO;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.SessaoPacienteDTO;
import com.bluefenix.api.Repositories.AtendenteRepository;
import com.bluefenix.api.Repositories.PacienteRepository;
import com.bluefenix.api.Security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PacienteRepository repositorioPaciente;

    @Autowired
    private AtendenteRepository repositorioAtendente;

    @Autowired TokenService tokenService;
    
    @PostMapping("/atendente/cadastro")
    public ResponseEntity<?> cadastrarAtendente(@RequestBody @Valid RegistroAtendenteDTO dados) {

        System.out.println(this.repositorioAtendente.findByEmail(dados.email()));

        if(this.repositorioAtendente.findByEmail(dados.email()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail já cadastrado");

        String senhaEncriptada;
        try {
            senhaEncriptada = new BCryptPasswordEncoder().encode(dados.senha());
        } catch(Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }

        Atendente atendenteCriado = new Atendente(dados.email(), dados.nome(), dados.cpf(), senhaEncriptada, dados.roles());

        this.repositorioAtendente.save(atendenteCriado);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/paciente/cadastro")
    public ResponseEntity<?> cadastrarPaciente(@RequestBody @Valid RegistroPacienteDTO dados) {

        if(this.repositorioPaciente.findByEmail(dados.email()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ja existe um usuario com este e-mail");

        String senhaEncriptada;
        try {
            senhaEncriptada = new BCryptPasswordEncoder().encode(dados.senha());
        } catch(Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }

        Paciente pacienteCriado = new Paciente(dados.nome(), dados.cpf(), dados.nascimento(), dados.numCarteirinha(), dados.rg(), dados.email(), senhaEncriptada, dados.pcd(), dados.roles());

        this.repositorioPaciente.save(pacienteCriado);
        
        return ResponseEntity.ok("Paciente criado com sucesso!");
    }

    @PostMapping("/atendente/login")
    public ResponseEntity<?> loginAtendente(@RequestBody @Valid CredentialsDTO dados) {
        UserDetails atendenteEncontrado = this.repositorioAtendente.findByEmail(dados.email());
        var emailESenha = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        Authentication autenticacao = null;

        try {
            autenticacao = this.authenticationManager.authenticate(emailESenha);
            System.out.println(autenticacao.getCredentials());
        } catch(BadCredentialsException error) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("dados_invalidos");

        } catch(NullPointerException error) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error.getMessage());

        } catch(InternalAuthenticationServiceException error) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("dados_invalidos");

        } catch(Exception error) {
            System.out.println("Ocorreu o seguinte erro ao tentar fazer login: " + error);
        }

        System.out.println("Informação que eu preciso: " + autenticacao.getPrincipal());

        var token = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new SessaoAtendenteDTO(dados.email(), "admin", token));
    }

    @PostMapping("/paciente/login")
    public ResponseEntity<?> loginPaciente(@RequestBody @Valid CredentialsDTO dados) {
        /* var emailESenha = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha()); */

        Paciente pacienteEncontrado = this.repositorioPaciente.findByEmail(dados.email());

        if(dados.email() != null && dados.senha() != null) {
            if(pacienteEncontrado != null) {
                if(dados.senha().equals(pacienteEncontrado.getSenha())) {
                   var token = tokenService.gerarToken((Usuario) pacienteEncontrado);
                    System.out.println(token);

                   return ResponseEntity.ok(new SessaoPacienteDTO(pacienteEncontrado.getNome(), pacienteEncontrado.getNumCarteirinha(), pacienteEncontrado.getRoles()));
                } else {
                    System.out.println("Usuário/senha inválidos");

                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Necessario preeencher ambos os campos");
        }
    }
}