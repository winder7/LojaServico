package com.waal.beans;

/**
 * @Autor Winder Rezende
 * @Data  23/05/2019
 */
public class ProdutoServico {
    
    private Produto produto;
    private Servico servico;
    private int quantidade = 1;

    public ProdutoServico() {
    }

    public ProdutoServico(Produto produto) {
        this.produto = produto;
    }
    
    public ProdutoServico(Servico servico) {
        this.servico = servico;
    }
    
    public ProdutoServico(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    public ProdutoServico(Servico servico, int quantidade) {
        this.servico = servico;
        this.quantidade = quantidade;
    }
    
    public ProdutoServico(Produto produto, Servico servico) {
        this.produto = produto;
        this.servico = servico;
    }

    public ProdutoServico(Produto produto, Servico servico, int quantidade) {
        this.produto = produto;
        this.servico = servico;
        this.quantidade = quantidade;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
