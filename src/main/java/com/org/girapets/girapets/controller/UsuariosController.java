package com.org.girapets.girapets.controller;


import com.org.girapets.girapets.dto.UsuariosDTO;
import com.org.girapets.girapets.model.Usuarios;
import com.org.girapets.girapets.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class UsuariosController {
    private final UsuariosService usuariosService;


    @Autowired
    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }


    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuariosDTO>> listarUsuarios() {
        List<Usuarios> usuarios = usuariosService.listarUsuarios();
        List<UsuariosDTO> usuariosDTO = new ArrayList<>();

        for (Usuarios usuario : usuarios) {
            UsuariosDTO usuarioDTO = new UsuariosDTO(usuario.getId(), usuario.getLogin(), usuario.getEmail(), usuario.getPassword());
            usuariosDTO.add(usuarioDTO);
        }

        return ResponseEntity.ok(usuariosDTO);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuariosDTO> obterUsuarioPorId(@PathVariable("id") Long id) {
        Usuarios usuario = usuariosService.buscarUsuariosPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        UsuariosDTO usuarioDTO = new UsuariosDTO(usuario.getId(), usuario.getLogin(), usuario.getEmail(), usuario.getPassword());
        return ResponseEntity.ok(usuarioDTO);
    }


    @PostMapping("/usuarios")
    public ResponseEntity<UsuariosDTO> adicionarUsuario(@RequestBody UsuariosDTO usuarioDTO) {
        Usuarios usuario = new Usuarios(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha());

        System.out.println(usuarioDTO.getNome() + usuarioDTO.getEmail() + usuarioDTO.getSenha());

        usuario = usuariosService.criarUsuarios(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<UsuariosDTO> atualizarUsuario(@PathVariable("id") Long id, @RequestBody UsuariosDTO usuarioDTO) {
        Usuarios usuarioExistente = usuariosService.buscarUsuariosPorId(id);

        if (usuarioExistente == null) {
            System.out.println("Nulo");
            return ResponseEntity.notFound().build();
        }

        usuarioExistente.setLogin(usuarioDTO.getNome());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        usuariosService.atualizarUsuarios(id, usuarioExistente);

        return ResponseEntity.ok(usuarioDTO);
    }
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable("id") Long id) {
        Usuarios usuarioExistente = usuariosService.buscarUsuariosPorId(id);

        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        usuariosService.excluirUsuarios(id);
        return ResponseEntity.noContent().build();
    }


}
