package com.waal.negocio;

import java.util.ArrayList;
import com.waal.beans.Produto;
import com.waal.beans.ProdutoServico;
import com.waal.beans.Servico;
import com.waal.persistencia.ProdutoDAO;
import com.waal.uteis.Exibir;
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
    private Produto produto = new Produto();
    private Servico servico = new Servico();
    private List<Produto> listaProdutos = new ArrayList<>();
    private List<ProdutoServico> listaProdServ = new ArrayList<>();

    public void actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (produto.getId() == 0) {
            ProdutoDAO.inserir(produto);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        } else {
            ProdutoDAO.alterar(produto);
            context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
        }
    }

    public void actionInserir(Object itens) {

        System.out.println(itens);
        
        if (itens.toString().contains("Produto")) {
            listaProdServ.add(new ProdutoServico((Produto) itens));
        } else if (itens.toString().contains("Servico")) {
            listaProdServ.add(new ProdutoServico((Servico) itens));
        }

//
//        for (int i = 0; i < listaProdServ.size(); i++) {
//            System.out.println(listaProdServ.get(i).getProduto().getNome());
//            System.out.println(listaProdServ.get(i).getServico().getNome());
//        }
    }

    public void actionExcluir(ProdutoServico itens) {
        listaProdServ.remove(itens);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Removido com sucesso!"));
    }

    public void actionLimpar() {
        listaProdServ.clear();
    }

    //GET-SET
    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
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
