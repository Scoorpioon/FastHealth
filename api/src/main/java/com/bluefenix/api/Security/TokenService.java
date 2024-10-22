package com.bluefenix.api.Security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bluefenix.api.Models.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.segredo}")
    private String shhh;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(shhh);

            String token = JWT.create()
                            .withIssuer("auth-api")
                            .withSubject(usuario.getEmail())
                            .withExpiresAt(gerarDataExpiracao())
                            .sign(algoritmo);

            System.out.println(" ");
            System.out.println("Token gerado:" + token);
            System.out.println(" ");

            return token;

        } catch(JWTCreationException error) {
            throw new RuntimeException("Vish, deu um erro ao gerar o token: " + error);
        }
    }

    public String validarToken(String token) {
        String tokenFormatado = token.replaceAll(" ", ""); // O Postman e o Insomnia estavam inserido um caractere de espaço no início do token, então tive que inserir essa função para que remova todos os espaços e valide o token normalmente. Demorei 2h pra encontrar essa merda de erro 

        try {
            Algorithm algoritmo = Algorithm.HMAC256(shhh);

            System.out.println(" ");
            System.out.println("Token informado pelo cliente" + token);
            System.out.println(" ");

            return JWT.require(algoritmo)
                    .withIssuer("auth-api")
                    .build()
                    .verify(tokenFormatado)
                    .getSubject();

        } catch(JWTVerificationException error) {
            System.out.println("Deu um erro na verificação (" + error + "). Retornando string vazia!");
            
            return "";
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}