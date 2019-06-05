package com.waal.uteis;

import com.waal.beans.FormaPgto;
import com.waal.beans.Pedido;
import com.waal.beans.Pessoa;

/**
 * @Autor Alexandre Almeida
 * @Data 25/05/2019
 */
public class BoletoDto {

    private Pessoa pessoa;
    private Pedido pedido;

    public BoletoDto(Pessoa pessoa, Pedido pedido) {
        this.pessoa = pessoa;
        this.pedido = pedido;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
