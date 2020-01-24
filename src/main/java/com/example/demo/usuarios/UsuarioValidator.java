package com.example.demo.usuarios;

public class UsuarioValidator {

    public static void validate(Usuario usuario) {

        if (usuario.getEmail() == null || usuario.getEmail().equals("") || !usuario.getEmail().matches("^(.+)@(.+)$"))
            throw new IllegalArgumentException("Email inválido");

        if (usuario.getName() == null || usuario.getName().equals("") || usuario.getName().matches("^(\\s+)$"))
            throw new IllegalArgumentException("Nome inválido");

        if (usuario.getPassword() == null || usuario.getPassword().replace(" ", "").isEmpty())
            throw new IllegalArgumentException("Senha inválida");

        if (usuario.getPhones() == null || usuario.getPhones().isEmpty())
            throw new IllegalArgumentException("É necessário pelo menos um telefone");

        if (usuario.getPhones().stream().noneMatch(i -> i.getNumber() != null && i.getNumber().matches("^(\\d+)$")))
            throw new IllegalArgumentException("Telefone nao e valido");

        if (usuario.getPhones().stream().noneMatch(i -> i.getDdd() != null && i.getDdd().matches("^(\\d+)$")))
            throw new IllegalArgumentException("Ddd nao e valido");

    }
}
