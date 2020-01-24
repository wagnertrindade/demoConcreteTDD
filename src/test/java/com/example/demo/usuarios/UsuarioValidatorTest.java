package com.example.demo.usuarios;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioValidatorTest {

    @InjectMocks
    private UsuarioValidator usuarioValidator;

    @Test(expected = IllegalArgumentException.class)
    public void dadoEmailNullodeveLancarIllegalException() {
        usuarioValidator.validate(Usuario.builder().build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoEmailEmptyodeveLancarIllegalException() {
        usuarioValidator.validate(Usuario.builder().email("").build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoEmaiInvalidodeveLancarIllegalException() {
        usuarioValidator.validate(Usuario.builder().email("xpto").build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoNomeNulldeveLancarIllegalException() {
        usuarioValidator.validate(Usuario.builder().email("jose@neto.com").build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoNomeEmptydeveLancarIllegalException() {
        usuarioValidator.validate(Usuario.builder().name("").email("jose@neto.com").build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoNomeComEspacoEmBrancodeveLancarIllegalException() {
        usuarioValidator.validate(Usuario.builder().name(" ").email("jose@neto.com").build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoPasswordEmBrancodeveLancarIllegalException() {
        usuarioValidator.validate(Usuario.builder().name("nome").email("jose@neto.com").password(" ").build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoPasswordNulodeveLancarIllegalException() {
        usuarioValidator.validate(Usuario.builder().name("nome").email("jose@neto.com").password(null).build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoPasswordVaziodeveLancarIllegalException() {
        usuarioValidator.validate(Usuario.builder().name("nome").email("jose@neto.com").password("").build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoListaTelefonesNulaDeveLancarIllegalException() {
        usuarioValidator.validate(Usuario.builder().name("nome").email("jose@neto.com").password("12345").phones(null).build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void dadoListaTelefonesVaziaDeveLancarIllegalException() {
        usuarioValidator.validate(Usuario.builder().name("nome").email("jose@neto.com").password("12345").phones(Collections.emptyList()).build());
    }

}