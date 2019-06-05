package com.waal.uteis;

import com.waal.beans.Pedido;
import com.waal.beans.Pessoa;

/**
 * @Autor Winder Rezende
 * @Data 05/06/2019
 */
public class Mensagem {

    public static String html(Pessoa pessoa, Pedido pedido) {

        String htmlMsg = "<!DOCTYPE html><html> <b><h1>Olá usuário!</h1></b><br> Seu pedido foi realizado na loja Waal Service, Valor: " + pedido.getTotalGeral() + ""
                + "<p> <b>OBS: </b> Esta mensagem é um teste...</p> </html>";

        return htmlMsg;
    }
}
