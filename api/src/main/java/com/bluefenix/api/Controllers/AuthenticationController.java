package com.bluefenix.api.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluefenix.api.Models.Atendente;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.AtAuthenticationDTO;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.AtRegisterDTO;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.LoginResponseDTO;
import com.bluefenix.api.Models.DTOs.SecurityDTOs.PaAuthenticationDTO;
import com.bluefenix.api.Repositories.AtendenteRepository;
/* import com.bluefenix.api.Repositories.PacienteRepository; */
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

    /* @Autowired
    private PacienteRepository pacienteRepository; */

    @Autowired
    private AtendenteRepository atendenteRepository;

    @Autowired TokenService tokenService;
    
    @PostMapping("/atendente/cadastro")
    public ResponseEntity<?> register(@RequestBody @Valid AtRegisterDTO dados) {

        System.out.println(this.atendenteRepository.findByEmail(dados.email()));

        if(this.atendenteRepository.findByEmail(dados.email()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail já cadastrado");

        String senhaEncriptada;

        try {
            senhaEncriptada = new BCryptPasswordEncoder().encode(dados.senha());
        } catch(Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }

        Atendente atendenteCriado = new Atendente(dados.email(), dados.nome(), dados.cpf(), senhaEncriptada, dados.roles());

        this.atendenteRepository.save(atendenteCriado);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/atendente/login")
    public ResponseEntity<?> loginAtendente(@RequestBody @Valid AtAuthenticationDTO dados) {

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

        var token = tokenService.gerarToken((Atendente) autenticacao.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/paciente/login")
    public ResponseEntity<?> loginPaciente(@RequestBody @Valid PaAuthenticationDTO dados) {
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