package com.org.girapets.girapets.services;


import com.org.girapets.girapets.model.Usuarios;
import com.org.girapets.girapets.repository.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuariosService {
    private final UsuariosRepository usuariosRepository;


    @Autowired
    public UsuariosService(UsuariosRepository usuarioRepository) {
        this.usuariosRepository = usuarioRepository;
    }

    public List<Usuarios> listarUsuarios() {

        return usuariosRepository.findAll();
    }

    public Usuarios buscarUsuariosPorId(Long id) {
        return usuariosRepository.findById(id).orElseThrow();

    }

    public Usuarios criarUsuarios(Usuarios usuarios) {
        // Realize validações ou lógica de negócio antes de salvar os usuários
        return usuariosRepository.save(usuarios);
    }


    public Usuarios atualizarUsuarios(Long id, Usuarios usuariosAtualizados) {
        Usuarios usuariosExistente = usuariosRepository.findById(id).orElseThrow();

        usuariosExistente.setName(usuariosAtualizados.getName());
        usuariosExistente.setEmail(usuariosAtualizados.getEmail());

        return usuariosRepository.save(usuariosExistente);
    }

    public void excluirUsuarios(Long id) {
        Usuarios usuarios = usuariosRepository.findById(id).orElseThrow();
        usuariosRepository.delete(usuarios);
    }
}
