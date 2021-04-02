package com.prova.util;

import java.util.ArrayList;
import java.util.Random;

public class Funcoes {



    public Integer converterDeStringParaInteger(String valorString){

        int valorInt = Integer.parseInt(valorString);
        return valorInt;
    }

    public static  String listarCPFComRestricoes(){


        ArrayList<String> listarCPF = new ArrayList<String>();
        listarCPF.add("97093236014");
        listarCPF.add("60094146012");
        listarCPF.add("84809766080");
        listarCPF.add("62648716050");
        listarCPF.add("26276298085");
        listarCPF.add("01317496094");
        listarCPF.add("55856777050");
        listarCPF.add("19626829001");
        listarCPF.add("24094592008");
        listarCPF.add("58063164083");


        Random randomGenerator = new Random();

        int index = randomGenerator.nextInt(listarCPF.size());
        String cpf = listarCPF.get(index);

        return cpf;
    }




}
