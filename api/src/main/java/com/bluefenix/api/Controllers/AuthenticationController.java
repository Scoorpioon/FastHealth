package com.bluefenix.api.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluefenix.api.Models.Atendente;
import com.bluefenix.api.Models.Paciente;
import com.bluefenix.api.Models.Usuario;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.CredentialsDTO;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.RegistroAtendenteDTO;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.RegistroPacienteDTO;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.LoginResponseDTO;
import com.bluefenix.api.Repositories.AtendenteRepository;
import com.bluefenix.api.Repositories.PacienteRepository;
import com.bluefenix.api.Security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

// Estou estudando sobre autenticação no Spring só agora. Eu acho que não necessariamente precisamos criar um controller somente para fazer autenticação, podemos inserir a autenticação em cada controller de usuário, como no de paciente e no de atendednte. Mas, por enquanto, vamo deixar assim mesmo. É bom que até organiza e deixa mais legível para eu ir relendo o código e estudando mais profundamente o conteúdo.

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

        var emailESenha = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        
        Authentication autenticacao = null;
        try {
            autenticacao = this.authenticationManager.authenticate(emailESenha);
            System.out.println(autenticacao.getCredentials());
        } catch(Exception error) {
            System.out.println("Ocorreu o seguinte erro ao tentar fazer login" + error);

            if(error instanceof BadCredentialsException) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha invalidos. Vaza daqui");
            }
        }

        var token = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/paciente/login")
    public ResponseEntity<?> loginPaciente(@RequestBody @Valid CredentialsDTO dados) {
        var emailESenha = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        try {
            var autenticacao = this.authenticationManager.authenticate(emailESenha);
            System.out.println(autenticacao.getCredentials());
        } catch(Exception error) {
            System.out.println("Ocorreu o seguinte erro ao tentar fazer login" + error);

            if(error instanceof BadCredentialsException) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha invalidos. Vaza daqui");
            }
        }

        return ResponseEntity.ok().build();
    }
}