package com.waal.uteis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * @Autor m159255
 * @Data 07/01/2019
 */
public class Subtrair {

    /**
     * Subtrai a quantidade especificada de dias a partir da data especificada
     *
     * @param data Informar a data ser subtraída no formato Date
     * @param dias Informar a quantidade de dias a ser subtraído
     * @param formatoSaida Informar a formato da data de saída ex: dd/MM/yyyy
     * @return String - Retorna a diferença de dias entre a data atual e a data
     * informada.
     *
     */
    public static String Data(Date data, int dias, String formatoSaida) {
        String dataSubtraida = "";
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(data);
            c.add(Calendar.DATE, -dias);
            data = c.getTime();

            dataSubtraida = new SimpleDateFormat(formatoSaida).format(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao subtrair data: " + e.getMessage());
            System.out.println("Erro ao subtrair data: " + e.getMessage());
        }

        return dataSubtraida;
    }
}
