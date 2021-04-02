package com.prova.test;

//import org.junit.Assert;
import com.prova.factory.SimulacaoDataFactory;
import com.prova.pojo.Simulacao;
import com.prova.util.Funcoes;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

public class RemoverSimulacoesTest {

    @Before
    public void Setup()  {
        // Configurações
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";

    }

    @Test
    public  void testRemoverSimulacoesComCadastro() throws IOException {

        Simulacao criarSimulacao = SimulacaoDataFactory.criarSimulacaoParaRemocao();

            given()
                    .contentType(ContentType.JSON)
                    .body(criarSimulacao)
                    .when()
                    .post("/v1/simulacoes")
                    .then();


        //Simulacoes por CPF
        Simulacao consultarCPF = SimulacaoDataFactory.criarSimulacaoParaRemocao();

        String valorJson =
                 given()
                .when()
                .get("/v1/simulacoes/"+consultarCPF.getCpf())
                .then()
                    .extract()
                         .path("id").toString();


        Funcoes funcoes = new Funcoes();
        int id = funcoes.converterDeStringParaInteger(valorJson);


        given()
                .when()
                .delete("/v1/simulacoes/"+id)
                .then()
                .log()
                .all()
                .statusCode(200);

    }

    @Test
    public  void testRemoverSimulacoesSemCadastro() throws IOException {


        int id = 999;

        given()
                .when()
                .delete("/v1/simulacoes/"+id)
                .then()
               .statusCode(200);

    }

}
