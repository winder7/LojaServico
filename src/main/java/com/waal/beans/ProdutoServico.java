package com.waal.beans;

/**
 * @Autor Winder Rezende
 * @Data  23/05/2019
 */
public class ProdutoServico {
    
    private Produto produto;
    private Servico servico;

    public ProdutoServico() {
    }

    public ProdutoServico(Produto produto) {
        this.produto = produto;
    }
    
    public ProdutoServico(Servico servico) {
        this.servico = servico;
    }
    
    public ProdutoServico(Produto produto, Servico servico) {
        this.produto = produto;
        this.servico = servico;
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
