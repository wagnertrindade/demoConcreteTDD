package com.example.demo.usuarios;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ModelMapper modelMap;

    @PostMapping(value = "/usuarios", consumes = "application/json")
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody UsuarioRequest usuarioRequest) {

        Usuario usuario = Usuario.builder()
                .name(usuarioRequest.getName())
                .email(usuarioRequest.getEmail())
                .password(usuarioRequest.getPassword())
                .phones(usuarioRequest.getPhones())
                .build();

        UsuarioResponse response = modelMap.map(usuarioService.cadastrar(usuario), UsuarioResponse.class);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping(value = "/usuarios", consumes = "application/json")
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {

        List<Usuario> usuarios = usuarioService.listarTodos();

        List<UsuarioResponse> response = usuarios.stream().map(i -> modelMap.map(i, UsuarioResponse.class)).collect(Collectors.toList());

        return ResponseEntity.status(200).body(response);
    }

}