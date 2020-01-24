package com.example.demo.usuarios;

import com.example.demo.DemoApplicationTests;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerTest extends DemoApplicationTests {

    @Test
    public void deveRetornar201() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(HttpStatus.CREATED.value(), statusCode);
    }

    @Test
    public void deveRetornarNome() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        String name = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().jsonPath().get("name");

        assertEquals(name, "João da Silva");
    }

    @Test
    public void deveRetornarEmail() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        String email = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().jsonPath().get("email");

        assertEquals(email, "joao@silva.org");
    }

    @Test
    public void deveRetornarSenha() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        String senha = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().jsonPath().get("password");

        assertEquals(senha, "hunter2");
    }

    @Test
    public void deveRetornarPhones() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        List phones = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().jsonPath().getList("phones");

        assertNotNull(phones);
    }

    @Test
    public void deveRetornarNumberPhone() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        List<Phone> phones = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().body().jsonPath().getList("phones", Phone.class);

        assertEquals(phones.get(0).getNumber(), "987654321");
    }

    @Test
    public void deveRetornarDddPhone() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        List<Phone> phones = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().body().jsonPath().getList("phones", Phone.class);

        assertEquals(phones.get(0).getDdd(), "21");
    }

    @Test
    public void dadoEmailVazioDeveRetornarStatusCode400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void dadoEmailNuloDeveRetornarStatusCode400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": null,\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void dadoEmailInvalidoDeveRetornarStatusCode400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"teste\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void dadoEmailComEspacoDeveRetornarStatusCode400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \" \",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void dadoNomeVazioDeveRetornarStatusCode400() {

        String json = "{\n" +
                "\t\"name\": \"\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void dadoNomeNuloDeveRetornarStatusCode400() {

        String json = "{\n" +
                "\t\"name\": null,\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void dadoNomeComEspacoDeveRetornarStatusCode400() {

        String json = "{\n" +
                "\t\"name\": \" \",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void dadoSenhaVazioDeveRetornarStatusCode400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void dadoSenhaNuloDeveRetornarStatusCode400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": null,\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void dadoNumeroTelefoneVazioDeveRetornarStatus400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void dadoNumeroTelefoneNuloDeveRetornarStatus400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": null,\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(HttpStatus.BAD_REQUEST.value(), statusCode);

    }

    @Test
    public void dadoNumeroTelefoneInvalidoDeveRetornarStatus400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"aaa\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void dadoNumeroDddVazioDeveRetornarStatus400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void dadoNumeroDddNuloDeveRetornarStatus400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": null\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void dadoNumeroDddInvalidoDeveRetornarStatus400() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"aaa\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        Integer statusCode = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().response().getStatusCode();

        assertEquals(statusCode, HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void dadoNomeVazioRetornarMensagemErroJson() {

        String json = "{\n" +
                "\t\"name\": \"\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        String body = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().body().asString();

        assertThat(body).isEqualTo("{\"mensagem\":\"Nome inválido\"}");
    }

    @Test
    public void dadoNomeNuloRetornarMensagemErroJson() {

        String json = "{\n" +
                "\t\"name\": null,\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        String body = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().body().asString();

        assertThat(body).isEqualTo("{\"mensagem\":\"Nome inválido\"}");
    }

    @Test
    public void dadoNomeInvalidoetornarMensagemErroJson() {

        String json = "{\n" +
                "\t\"name\": \" \",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        String body = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().body().asString();

        assertThat(body).isEqualTo("{\"mensagem\":\"Nome inválido\"}");
    }

    @Test
    public void dadoEmailVazioRetornarMensagemErroJson() {

        String json = "{\n" +
                "\t\"name\": \"teste da silva\",\n" +
                "\t\"email\": \"\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        String body = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().body().asString();

        assertThat(body).isEqualTo("{\"mensagem\":\"Email inválido\"}");
    }

    @Test
    public void dadoEmailNuloRetornarMensagemErroJson() {

        String json = "{\n" +
                "\t\"name\": \"teste da silva\",\n" +
                "\t\"email\": null,\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        String body = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().body().asString();

        assertThat(body).isEqualTo("{\"mensagem\":\"Email inválido\"}");
    }

    @Test
    public void dadoEmailInvalidoetornarMensagemErroJson() {

        String json = "{\n" +
                "\t\"name\": \"teste da silva\",\n" +
                "\t\"email\": \"aaa\",\n" +
                "\t\"password\": \"hunter2\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        String body = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().body().asString();

        assertThat(body).isEqualTo("{\"mensagem\":\"Email inválido\"}");
    }

    @Test
    public void dadoSenhaVaziaRetornarMensagemErroJson() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": \"\",\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        String body = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().body().asString();

        assertThat(body).isEqualTo("{\"mensagem\":\"Senha inválida\"}");
    }

    @Test
    public void dadoSenhaNulaRetornarMensagemErroJson() {

        String json = "{\n" +
                "\t\"name\": \"João da Silva\",\n" +
                "\t\"email\": \"joao@silva.org\",\n" +
                "\t\"password\": null,\n" +
                "\t\"phones\": [\n" +
                "\t    {\n" +
                "\t\t\"number\": \"987654321\",\n" +
                "\t\t\"ddd\": \"21\"\n" +
                "\t    }\n" +
                "\t]\n" +
                "}\n";

        String body = RestAssured.given().contentType(MediaType.APPLICATION_JSON.toString())
                .body(json)
                .post(url("/usuarios"))
                .then().extract().body().asString();

        assertThat(body).isEqualTo("{\"mensagem\":\"Senha inválida\"}");
    }


    /* Feature Listar todos os usuários */

    @Test
    public void dadoSolicitacaoTodosUsuariosDeveRetornarStatus200() {

        Integer statusCode = RestAssured.given()
                .get(url("/todos"))
                .then().extract().response().getStatusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void dadoSolicitacaoTodosUsuariosDeveRetornarListaDeUsuarios() {

        List<Usuario> lista = RestAssured.given()
                .get(url("/todos"))
                .then().extract().body().jsonPath().getList("usuarios", Usuario.class);

        assertThat(lista.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void dadoSolicitacaoTodosUsuariosDeveRetornarUsuariosComNomesValidos() {

        List<Usuario> lista = RestAssured.given()
                .get(url("/todos"))
                .then().extract().body().jsonPath().getList("usuarios", Usuario.class);

        assertThat(lista.stream().allMatch(i -> i.getName() != null && !i.getName().replace(" ", "").isEmpty()))
                .isTrue();
    }

    @Test
    public void dadoSolicitacaoTodosUsuariosDeveRetornarUsuariosComEmailsValidos() {

        List<Usuario> lista = RestAssured.given()
                .get(url("/todos"))
                .then().extract().body().jsonPath().getList("usuarios", Usuario.class);

        assertThat(lista.stream().allMatch(i -> i.getEmail() != null && !i.getEmail().replace(" ", "").isEmpty() && i.getEmail().matches("^(.+)@(.+)$")))
                .isTrue();
    }

    @Test
    public void dadoSolicitacaoTodosUsuariosDeveRetornarUsuariosComSenhasValidas() {

        List<Usuario> lista = RestAssured.given()
                .get(url("/todos"))
                .then().extract().body().jsonPath().getList("usuarios", Usuario.class);

        assertThat(lista.stream().allMatch(i -> i.getPassword() != null && !i.getPassword().replace(" ", "").isEmpty()))
                .isTrue();
    }

}