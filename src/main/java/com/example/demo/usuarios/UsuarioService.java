package com.example.demo.usuarios;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public void cadastrar(Usuario usuario) {
        UsuarioValidator.validate(usuario);
        usuarioRepository.save(usuario);
    }

}
