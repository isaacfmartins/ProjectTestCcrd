package com.prova.test;

//import org.junit.Assert;
import com.prova.factory.SimulacaoDataFactory;
import com.prova.pojo.Simulacao;
import com.prova.util.Funcoes;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

public class AtualizarSimulacoesTest {

    @Before
    public void Setup(){
        // Configurações
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";
    }

    @Test
    public  void testAlterarSimulacoesComSucesso() throws IOException {

        Simulacao criarSimulacao = SimulacaoDataFactory.criarSimulacaoParaAlteracao();

        given()
                .contentType(ContentType.JSON)
                .body(criarSimulacao)
                .when()
                .post("/v1/simulacoes")
                .then()
                .log()
                .all();

        Simulacao alterarSimulacao = SimulacaoDataFactory.alterarSimulacaoComSucesso();

        given()
                .contentType(ContentType.JSON)
                .body(alterarSimulacao)
                .when()
                .put("/v1/simulacoes/"+alterarSimulacao.getCpf())
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("nome",Matchers.equalTo("NomeAlterado"))
                .body("email",Matchers.equalTo("emailALT@email.com"))
                .body("parcelas",Matchers.equalTo(12))
                .body("seguro",Matchers.equalTo(false));


        Simulacao consultarCPF = SimulacaoDataFactory.criarSimulacaoParaAlteracao();

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
                .statusCode(200);
    }

    @Test
    public  void testAlterarSimulacoesComCPFSemCadastro() throws IOException {

        Simulacao alterarSimulacao = SimulacaoDataFactory.alterarSimulacaoComCPFSemCadastro();

        given()
                .contentType(ContentType.JSON)
                .body(alterarSimulacao)
                .when()
                .put("/v1/simulacoes/"+alterarSimulacao.getCpf())
                .then()
                .statusCode(404)
                .body("mensagem", Matchers.equalTo("CPF "+alterarSimulacao.getCpf()+" não encontrado"));

    }


}
