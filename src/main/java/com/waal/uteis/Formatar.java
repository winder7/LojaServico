package com.waal.uteis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Autor m159255
 * @Data 08/10/2018
 */
public class Formatar {

    public static String Data(String data, String formatoEntrada, String formatoSaida) {
        String dataForm = "";
        try {
            Date date = new SimpleDateFormat(formatoEntrada).parse(data);
            dataForm = new SimpleDateFormat(formatoSaida).format(date);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return dataForm;
    }
    
    public static String data(Date date, String formatoSaida) {
        String dataForm = "";
        try {
            dataForm = new SimpleDateFormat(formatoSaida).format(date);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return dataForm;
    }
    
    public static String formatarNumero(double num) {
        try {
            return String.format("R$ " + "%,.2f", num);
        } catch (Exception e) {
            System.out.println("Erro na formatação de número!: " + e);
            return "Erro";
        }
    }
}
