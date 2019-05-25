package com.waal.uteis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * @Autor m159255
 * @Data 07/01/2019
 */
public class Somar {

    /**
     * Subtrai a quantidade especificada de dias a partir da data especificada
     *
     * @param data Informar a data ser subtraída no formato Date
     * @param dias Informar a quantidade de dias a ser subtraído
     * @return String - Retorna a diferença de dias entre a data atual e a data
     * informada.
     *
     */
    public static Date Data(Date data, int dias) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(data);
            c.add(Calendar.DATE, +dias);
            data = c.getTime();

        } catch (Exception e) {
            System.out.println("Erro ao subtrair data: " + e.getMessage());
            return new Date();
        }

        return data;
    }
}
