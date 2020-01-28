package com.example.demo.usuarios;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetups({@DatabaseSetup(value = "/datasets/zerar-banco.xml", type = DatabaseOperation.TRUNCATE_TABLE), @DatabaseSetup("/datasets/usuarios-test.xml")})
@DatabaseTearDown(value = "/datasets/zerar-banco.xml", type = DatabaseOperation.TRUNCATE_TABLE)
@SpringBootTest
public class UsuarioServiceIntegrationTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

//    @BeforeEach
//    public void setup() {
//        usuarioRepository.deleteAll();
//    }

//    @AfterEach
//    public void tearDown() {
//        usuarioRepository.deleteAll();
//    }

    @Test
    public void dadoUsuarioValidoDeveSalvarNoBanco() {
        Usuario usuarioSalvar = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .password("12345")
                .phones(Arrays.asList(Phone.builder().ddd("21").number("987654321").build()))
                .build();

        Usuario usuarioSalvo = usuarioService.cadastrar(usuarioSalvar);

        assertThat(usuarioSalvo).isNotNull();
    }

    @Test
    public void dadoUsuarioInvalidoNaoDeveSalvarNoBanco() {
        Usuario usuarioSalvar = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .build();

        Usuario usuarioSalvo = null;
        try {
            usuarioSalvo = usuarioService.cadastrar(usuarioSalvar);
        } catch (Exception e) {
        }

        assertThat(usuarioSalvo).isNull();
    }

    @Test
    void deveSalvarNomeNoBanco() {

        Usuario usuarioSalvar = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .password("12345")
                .phones(Arrays.asList(Phone.builder().ddd("21").number("987654321").build()))
                .build();

        Usuario usuarioSalvo = usuarioService.cadastrar(usuarioSalvar);

        assertThat(usuarioSalvo.getName()).isEqualTo(usuarioSalvar.getName());
    }

    @Test
    void deveSalvarEmailNoBanco() {

        Usuario usuarioSalvar = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .password("12345")
                .phones(Arrays.asList(Phone.builder().ddd("21").number("987654321").build()))
                .build();

        Usuario usuarioSalvo = usuarioService.cadastrar(usuarioSalvar);

        assertThat(usuarioSalvo.getEmail()).isEqualTo(usuarioSalvar.getEmail());
    }

    @Test
    void deveSalvarTelefoneNoBanco() {

        Usuario usuarioSalvar = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .password("12345")
                .phones(Arrays.asList(Phone.builder().ddd("21").number("987654321").build()))
                .build();

        Usuario usuarioSalvo = usuarioService.cadastrar(usuarioSalvar);

        assertThat(usuarioSalvo.getPhones().get(0).getNumber()).isEqualTo(usuarioSalvar.getPhones().get(0).getNumber());
    }

    @Test
    void deveSalvarDddNoBanco() {

        Usuario usuarioSalvar = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .password("12345")
                .phones(Arrays.asList(Phone.builder().ddd("21").number("987654321").build()))
                .build();

        Usuario usuarioSalvo = usuarioService.cadastrar(usuarioSalvar);

        assertThat(usuarioSalvo.getPhones().get(0).getDdd()).isEqualTo(usuarioSalvar.getPhones().get(0).getDdd());
    }

    @Test
    void deveSalvarPasswordNoBanco() {

        Usuario usuarioSalvar = Usuario.builder()
                .name("teste")
                .email("teste@teste")
                .password("12345")
                .phones(Arrays.asList(Phone.builder().ddd("21").number("987654321").build()))
                .build();

        Usuario usuarioSalvo = usuarioService.cadastrar(usuarioSalvar);

        assertEquals(usuarioSalvar.getPassword(), usuarioSalvo.getPassword());
    }

    @Test
    void deveRetornarListaDeUsuarios() {

        List<Usuario> usuarios = usuarioService.listarTodos();

        assertThat(usuarios.size()).isGreaterThan(0);
    }

    @Test
    void deve_retornar_nome_correto() {

        List<Usuario> usuarios = usuarioService.listarTodos();

        assertThat(usuarios.get(0).getName()).isEqualTo("João da Silva");

    }

    @Test
    void deve_retornar_email_correto() {

        List<Usuario> usuarios = usuarioService.listarTodos();

        assertThat(usuarios.get(0).getEmail()).isEqualTo("joao@silva.org");

    }

    @Test
    void deve_retornar_password_correto() {

        List<Usuario> usuarios = usuarioService.listarTodos();

        assertThat(usuarios.get(0).getPassword()).isEqualTo("hunter2");

    }

}
