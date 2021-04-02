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

public class RetornarSimulacoesTest {


    @Before
    public void Setup(){
        // Configurações
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";
    }



    @Test
    public void testeConsultarTodasSimulacoes(){
                given()
                .when()
                        .get("/v1/simulacoes")
                .then()
                        .statusCode(200)
                ;


    }


    @Test
    public  void testeConsultarSimulacoesPorCPFComSucesso() throws IOException {

                Simulacao consultarCPF = SimulacaoDataFactory.consultarSimulacoesPorCPFComSucesso();
                given()
                .when()
                    .get("/v1/simulacoes/"+consultarCPF.getCpf())
                .then()
                        .statusCode(200)
                        .body("cpf", Matchers.equalTo(consultarCPF.getCpf()));
    }

    @Test
    public  void testeConsultarSimulacoesPorCPFSemSucesso() throws IOException {

        Simulacao consultarCPF = SimulacaoDataFactory.consultarSimulacoesPorCPFSemCadastro();
        given()
                .when()
                .get("/v1/simulacoes/"+consultarCPF.getCpf())
                .then()
                .statusCode(404)
                .body("mensagem", Matchers.equalTo("CPF "+consultarCPF.getCpf()+" não encontrado"));
    }



}


