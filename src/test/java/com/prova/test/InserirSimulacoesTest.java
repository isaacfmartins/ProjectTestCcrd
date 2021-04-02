package com.prova.test;

//import org.junit.Assert;
import com.prova.factory.SimulacaoDataFactory;
import com.prova.pojo.Simulacao;
import com.prova.util.Funcoes;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

public class InserirSimulacoesTest {


    @Before
    public void Setup() {
        // Configurações
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";
    }

    @Test
    public void testInserirSimulacoesComSucesso() throws IOException {

        Simulacao criarSimulacao = SimulacaoDataFactory.criarSimulacaoComSucesso();

        given()
                .contentType(ContentType.JSON)
                .body(criarSimulacao)
                .when()
                .post("/v1/simulacoes")
                .then()
                .statusCode(201)
                .body("cpf", Matchers.equalTo("63182688049"));


        Simulacao consultarCPF = SimulacaoDataFactory.criarSimulacaoComSucesso();

        String valorJson =
                given()
                        .when()
                        .get("/v1/simulacoes/" + consultarCPF.getCpf())
                        .then()
                        .extract()
                        .path("id").toString();


        Funcoes funcoes = new Funcoes();
        int id = funcoes.converterDeStringParaInteger(valorJson);


        given()
                .when()
                .delete("/v1/simulacoes/" + id)
                .then();
    }

    @Test
    public void testInserirSimulacoesComInformacoesFaltantes() throws IOException {

        Simulacao criarSimulacao = SimulacaoDataFactory.criarSimulacaoComInformacoesFaltantes();

        given()
                .contentType(ContentType.JSON)
                .body(criarSimulacao)
                .when()
                .post("/v1/simulacoes")
                .then()
                .statusCode(400)
                .body("erros.parcelas", Matchers.equalTo("Parcelas deve ser igual ou maior que 2"))
                .body("erros.valor", Matchers.equalTo("Valor deve ser menor ou igual a R$ 40.000"))
                .body("erros.cpf", Matchers.equalTo("CPF não pode ser vazio"))
                .body("erros.nome", Matchers.equalTo("Nome não pode ser vazio"))
                .body("erros.email", Matchers.equalTo("E-mail deve ser um e-mail válido"));

    }

    @Test
    public void testInserirSimulacoesComCPFCadastrado() throws IOException {

        Simulacao criarSimulacao = SimulacaoDataFactory.criarSimulacaoComCPFCadastrado();

        given()
                .contentType(ContentType.JSON)
                .body(criarSimulacao)
                .when()
                .post("/v1/simulacoes")
                .then()
                .statusCode(400)
                .body("mensagem", Matchers.equalTo("CPF duplicado"));

    }
}