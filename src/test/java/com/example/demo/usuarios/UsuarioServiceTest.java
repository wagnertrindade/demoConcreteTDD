package com.example.demo.usuarios;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @Test(expected = IllegalArgumentException.class)
    public void dadoEmailNullodeveLancarIllegalException() {
        usuarioService.cadastrar(Usuario.builder().build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoEmailEmptyodeveLancarIllegalException() {
        usuarioService.cadastrar(Usuario.builder().email("").build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoEmailValidodeveLancarIllegalException() {
        usuarioService.cadastrar(Usuario.builder().email("xpto").build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoNomeNullodeveLancarIllegalException() {
        usuarioService.cadastrar(Usuario.builder().name(null).build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoNomeVazioDeveLancarIllegalException() {
        usuarioService.cadastrar(Usuario.builder().name("").build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoNomeEmBrancoDeveLancarIllegalException() {
        usuarioService.cadastrar(Usuario.builder().name(" ").build());
    }

    @Test
    public void dadoUsuarioValidoDeveRealizarCadastro() {
        Usuario usuario = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .password("12345")
                .phones(Arrays.asList(Phone.builder().ddd("21").number("987654321").build()))
                .build();

        usuarioService.cadastrar(usuario);
        Mockito.verify(usuarioRepository, times(1)).save(any());
    }

    @Test
    public void dadoUsuarioInvalidoNaoDeveInvocarRepository() {
        Usuario usuario = Usuario.builder()
                .name("teste")
                .password("12345")
                .build();

        try {
            usuarioService.cadastrar(usuario);
        } catch (Exception e) {
        }

        Mockito.verify(usuarioRepository, times(0)).save(any());
    }

}