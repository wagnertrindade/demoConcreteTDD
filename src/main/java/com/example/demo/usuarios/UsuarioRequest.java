package com.example.demo.usuarios;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioRequest {

    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
}
