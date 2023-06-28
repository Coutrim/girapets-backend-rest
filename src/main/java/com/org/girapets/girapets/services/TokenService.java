package com.org.girapets.girapets.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.org.girapets.girapets.model.Usuarios;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class TokenService {

    public static final int TOKEN_EXPIRACAO = 60_000;

    private final Algorithm algorithm;

    public TokenService() {
        this.algorithm = Algorithm.HMAC256("5Il9EcIE2wWFhrbIb19FSgSsv2cPFRrlRE1NkHewDX3QA0KtrEx2WemJ6TVpbPm5ShjTrY6ESzfmIt0A3IhFoAg9KgPKE6VowqxMeqbNNR68dIOddiUOhx6WulsxEu");
    }

    public String gerarToken(Usuarios usuario) {

        ZonedDateTime expirationTime = ZonedDateTime.now()
                .plusHours(3)
                .withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));

        return JWT.create()
                .withIssuer("Animais")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                //.withExpiresAt(Date.from(LocalDateTime.now().plusSeconds(TOKEN_EXPIRACAO).toInstant(ZoneOffset.UTC)))
                .withExpiresAt(Date.from(expirationTime.toInstant()))
                .sign(algorithm);
    }

    public String getSubject(String token) {
        return JWT.require(algorithm)
                .withIssuer("Animais")
                .build()
                .verify(token)
                .getSubject();
    }
}
