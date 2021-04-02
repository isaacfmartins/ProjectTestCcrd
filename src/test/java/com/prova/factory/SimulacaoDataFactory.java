    package com.prova.factory;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.prova.pojo.Simulacao;

    import java.io.FileInputStream;
    import java.io.IOException;


    public class SimulacaoDataFactory {

        public static Simulacao simulacaoValida() throws IOException {

            ObjectMapper objectMapper = new ObjectMapper();
            Simulacao simulacao = objectMapper.readValue(new FileInputStream("src/test/resources/requestBody/simulacoes.json"),Simulacao.class);
            return simulacao;
        }


        public static Simulacao consultarSimulacoesPorCPFComSucesso() throws IOException {
            Simulacao consultarCPF = simulacaoValida();
            consultarCPF.setCpf("17822386034");
            return consultarCPF;

        }

        public static Simulacao consultarSimulacoesPorCPFSemCadastro() throws IOException {
            Simulacao consultarCPF = simulacaoValida();
            consultarCPF.setCpf("04107208010");
            return consultarCPF;

        }

       public static Simulacao criarSimulacaoComSucesso() throws IOException {
           Simulacao criarSimulacao = simulacaoValida();
           criarSimulacao.setCpf("63182688049");
           return criarSimulacao;
       }

        public static Simulacao criarSimulacaoComInformacoesFaltantes() throws IOException {
            Simulacao criarSimulacao = simulacaoValida();
            criarSimulacao.setCpf(null);
            criarSimulacao.setNome(null);
            criarSimulacao.setEmail("");
            criarSimulacao.setValor(40001);
            criarSimulacao.setParcelas(1);
            return criarSimulacao;
        }

        public static Simulacao criarSimulacaoComCPFCadastrado() throws IOException {
            Simulacao criarSimulacao = simulacaoValida();
            criarSimulacao.setCpf("66414919004");
            return criarSimulacao;
        }

        public static Simulacao criarSimulacaoParaRemocao() throws IOException {
            Simulacao criarSimulacao = simulacaoValida();
            criarSimulacao.setCpf("20768236037");
            return criarSimulacao;
        }


        public static Simulacao criarSimulacaoParaAlteracao() throws IOException {
            Simulacao criarSimulacao = simulacaoValida();
            criarSimulacao.setCpf("85891066084");
            return criarSimulacao;
        }



        public static Simulacao alterarSimulacaoComSucesso() throws IOException {


            Simulacao alterarSimulacao = criarSimulacaoParaAlteracao();
            alterarSimulacao.setNome("NomeAlterado");
            alterarSimulacao.setEmail("emailALT@email.com");
            alterarSimulacao.setParcelas(12);
            alterarSimulacao.setSeguro(false);
            return alterarSimulacao;
        }

        public static Simulacao alterarSimulacaoComCPFSemCadastro() throws IOException {
            Simulacao alterarSimulacao = simulacaoValida();
            alterarSimulacao.setCpf("25857718066");
            alterarSimulacao.setParcelas(6);
            return alterarSimulacao;
        }






    }
