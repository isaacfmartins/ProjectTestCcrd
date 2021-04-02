package com.prova.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prova.pojo.Restricao;
import com.prova.util.Funcoes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RestricoesDataFactory {

    public static Restricao Restricoes() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Restricao restricoes = objectMapper.readValue(new FileInputStream("src/test/resources/requestBody/restricoes.json"), Restricao.class);
        return restricoes;
    }

    public static Restricao consultarCPFComRestricoes() throws IOException {
        Restricao consultarCPF = Restricoes();
        Funcoes funcoes = new Funcoes();
        consultarCPF.setCpf(funcoes.listarCPFComRestricoes());
        return consultarCPF;
    }

    public static Restricao consultarCPFSemRestricoes() throws IOException {
        Restricao consultarCPF = Restricoes();
        consultarCPF.setCpf("05948031012");
        return consultarCPF;
    }


}
