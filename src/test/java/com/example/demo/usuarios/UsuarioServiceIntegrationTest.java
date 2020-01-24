package com.example.demo.usuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo: USAR ASSERTDB
@SpringBootTest
public class UsuarioServiceIntegrationTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setup() {
        usuarioRepository.deleteAll();
    }

    @Test
    public void dadoUsuarioValidoDeveSalvarNoBanco() {
        Usuario usuario = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .password("12345")
                .phones(Arrays.asList(Phone.builder().ddd("21").number("987654321").build()))
                .build();

        usuarioService.cadastrar(usuario);

        List<Usuario> usuarios = usuarioRepository.findAll();
        assertEquals(usuarios.size(), 1);
    }

    @Test
    public void dadoUsuarioInvalidoNaoDeveSalvarNoBanco() {
        Usuario usuario = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .build();

        try {
            usuarioService.cadastrar(usuario);
        } catch (Exception e) {
        }

        List<Usuario> usuarios = usuarioRepository.findAll();
        assertEquals(0, usuarios.size());
    }

    @Test
    void deveSalvarNomeNoBanco() {

        Usuario usuario = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .password("12345")
                .phones(Arrays.asList(Phone.builder().ddd("21").number("987654321").build()))
                .build();

        usuarioService.cadastrar(usuario);

        List<Usuario> usuarios = usuarioRepository.findAll();
        assertEquals(usuarios.get(0).getName(), "teste");
    }

    @Test
    void deveSalvarEmailNoBanco() {

        Usuario usuario = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .password("12345")
                .phones(Arrays.asList(Phone.builder().ddd("21").number("987654321").build()))
                .build();

        usuarioService.cadastrar(usuario);

        List<Usuario> usuarios = usuarioRepository.findAll();
        assertEquals(usuarios.get(0).getEmail(), "teste@teste");
    }

    @Test
    void deveSalvarPasswordNoBanco() {

        Usuario usuario = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .password("12345")
                .phones(Arrays.asList(Phone.builder().ddd("21").number("987654321").build()))
                .build();

        usuarioService.cadastrar(usuario);

        List<Usuario> usuarios = usuarioRepository.findAll();
        assertEquals(usuarios.get(0).getPassword(), "12345");
    }

}
