package com.waal.uteis;

import com.waal.beans.Pedido;
import com.waal.beans.Pessoa;

/**
 * @Autor Winder Rezende
 * @Data 05/06/2019
 */
public class Mensagem {

    public static String html(Pessoa pessoa, Pedido pedido) {
        
        String htmlMsg = "<!DOCTYPE html><html><div style=\"width: 560px; margin: auto;\"><center><img src=\"D:\\imagem\\waalName.jpg\" style=\"width: 550px;\"/>" +
                "<b><h1>Olá " + pessoa.getNome() + "!</h1></b><br>" +
                "<h3>Seu pedido número " + pedido.getId() + " foi realizado na loja Waal Service no dia " + pedido.getDataEmissao() + " com sucesso!</h3></center>" +
                "<div style=\"padding: 10px;\"><b>Detalhes:</b><br>" +
                "<p>Status: " + pedido.getStatus() + 
                "<br>Total em produtos: " + pedido.getTotalProduto() + 
                "<br>Total em serviços: " + pedido.getTotalServico() + 
                "<br>Descontos: " + pedido.getDesconto() + 
                "<br>Total geral: " + pedido.getTotalGeral() + 
                "</p></div></div></html>";
        
        return htmlMsg;
    }
}
