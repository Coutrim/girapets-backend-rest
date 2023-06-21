package com.org.girapets.girapets.controller;

import com.org.girapets.girapets.dto.LoginDTO;
import com.org.girapets.girapets.dto.LoginResponseDTO;
import com.org.girapets.girapets.model.Usuarios;
import com.org.girapets.girapets.services.AuthenticationService;
import com.org.girapets.girapets.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final AuthenticationService authenticationService;

    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthenticationService authenticationService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Usuarios usuario = (Usuarios) authentication.getPrincipal();

        String token = tokenService.gerarToken(usuario);

        // Criar uma instância de LoginResponseDTO e definir os valores
        LoginResponseDTO loginResponseDto = new LoginResponseDTO();
        loginResponseDto.setNomeUsuario(usuario.getLogin());
        loginResponseDto.setToken(token);

        return loginResponseDto;
    }

}
