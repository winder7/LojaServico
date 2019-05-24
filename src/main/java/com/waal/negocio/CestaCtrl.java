package com.waal.negocio;

import java.util.ArrayList;
import com.waal.beans.Produto;
import com.waal.beans.ProdutoServico;
import com.waal.beans.Servico;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @Autor Winder Rezende
 * @Data 27/02/2019, 20:45:21
 */
@ManagedBean
@SessionScoped
public class CestaCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private String filtro = "";
    private List<Produto> listaProdutos = new ArrayList<>();
    private List<ProdutoServico> listaProdServ = new ArrayList<>();

    public void addCesta(Produto produto) {
        listaProdServ.add(new ProdutoServico(produto));
        imprimeProdServ();
    }

    public void addCesta(ProdutoServico prodServ) {
        if(prodServ.getProduto() != null ){
            addCesta(prodServ.getProduto());
        } else {
            addCesta(prodServ.getServico());
        }
    }

    public void addCesta(Servico servico) {
        listaProdServ.add(new ProdutoServico(servico));
        imprimeProdServ();
    }

    public void imprimeProdServ() {
        for (int i = 0; i < listaProdServ.size(); i++) {
            if (listaProdServ.get(i).getProduto() != null) {
                System.out.println(listaProdServ.get(i).getProduto().getNome());
            } else {
                System.out.println(listaProdServ.get(i).getServico().getNome());
            }
        }
    }

    public void actionExcluir(ProdutoServico itens) {
        listaProdServ.remove(itens);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Removido com sucesso!"));
    }

    public void actionLimpar() {
        listaProdServ.clear();
    }

    public int getImagem(Produto produto) {
        return produto.getImagens().get(0).getId();
    }

    public int getImagem(Servico servico) {
        return servico.getImagens().get(0).getId();
    }

    //GET-SET
    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public List<ProdutoServico> getListaProdServ() {
        return listaProdServ;
    }

    public void setListaProdServ(List<ProdutoServico> listaProdServ) {
        this.listaProdServ = listaProdServ;
    }
}
