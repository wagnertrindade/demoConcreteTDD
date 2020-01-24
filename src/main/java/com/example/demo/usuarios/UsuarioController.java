package com.example.demo.usuarios;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    
    @PostMapping("/usuarios")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioRequest usuarioRequest) {

        Usuario usuario = Usuario.builder()
                .name(usuarioRequest.getName())
                .email(usuarioRequest.getEmail())
                .password(usuarioRequest.getPassword())
                .phones(usuarioRequest.getPhones())
                .build();

        try {

            usuarioService.cadastrar(usuario);

        } catch(IllegalArgumentException e) {
            return ResponseEntity.status(400).body("{\"mensagem\":\"" + e.getMessage() + "\"}");
        }
        
        UsuarioResponse response = UsuarioResponse.builder()
                .name(usuario.getName())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .phones(usuario.getPhones())
                .build();

//        if (usuarioRequest.getName() == null || usuarioRequest.getName().equals("") || usuarioRequest.getName().matches("^(\\s+)$"))
//            return ResponseEntity.status(400).body("{\"mensagem\":\"Nome inválido\"}");
//
//        if (usuarioRequest.getEmail() == null || usuarioRequest.getEmail().equals("") || !usuarioRequest.getEmail().matches("^(.+)@(.+)$"))
//            return ResponseEntity.status(400).body("{\"mensagem\":\"Email inválido\"}");
//
//        if (usuarioRequest.getPassword() == null || usuarioRequest.getPassword().equals(""))
//            return ResponseEntity.status(400).body("{\"mensagem\":\"Senha inválida\"}");
//
//        String number = usuarioRequest.getPhones().get(0).getNumber();
//        if (number == null || number.equals("") || !number.matches("^(\\d+)$"))
//            return ResponseEntity.status(400).build();
//
//        String ddd = usuarioRequest.getPhones().get(0).getDdd();
//        if (ddd == null || ddd.equals("") || !ddd.matches("^(\\d+)$"))
//            return ResponseEntity.status(400).build();
//
        return ResponseEntity.status(201).body(response);
    }

}