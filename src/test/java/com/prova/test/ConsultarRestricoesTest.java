package com.prova.test;

//import org.junit.Assert;
import com.prova.factory.RestricoesDataFactory;
import com.prova.factory.SimulacaoDataFactory;
import com.prova.pojo.Restricao;
import com.prova.pojo.Simulacao;
import com.prova.util.Funcoes;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

public class ConsultarRestricoesTest {


    @Before
    public void Setup() {
        // Configurações
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";
    }

    @Test
    public void testConsultarCPFComRestricoes() throws IOException {
        Restricao consultarRestricao = RestricoesDataFactory.consultarCPFComRestricoes();

        given()
                .when()
                .get("/v1/restricoes/" + consultarRestricao.getCpf())
                .then()
                .statusCode(200)
                .body("mensagem", Matchers.is("O CPF " +consultarRestricao.getCpf()+ " tem problema"));
    }

    @Test
    public void testConsultarCPFSemRestricoes() throws IOException {
        Restricao consultarRestricao = RestricoesDataFactory.consultarCPFSemRestricoes();

        given()
                .when()
                .get("/v1/restricoes/" + consultarRestricao.getCpf())
                .then()
                .statusCode(204);
    }

}