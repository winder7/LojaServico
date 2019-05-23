package com.waal.negocio;

import java.util.ArrayList;
import com.waal.beans.Produto;
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
public class CarrinhoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private String filtro = "";
    private Produto produto = new Produto();
    private Servico servico = new Servico();
    private List<Produto> listaProdutos = new ArrayList<>();
    private List<Object> listaProdServ = new ArrayList<>();

    public List<Object> getListagem() {
        return listaProdServ;
    }

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
        listaProdServ.add(itens);
        
        for (int i = 0; i < listaProdServ.size(); i++) {
            if (listaProdServ.get(i).toString().contains("Produto")) {
                System.out.println(((Produto) listaProdServ.get(i)).getNome());
            }
            if (listaProdServ.get(i).toString().contains("Servico")) {
                System.out.println(((Servico) listaProdServ.get(i)).getNome());
            }
        }
    }

    public void actionExcluir(Object itens) {
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

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public List<Object> getListaProdServ() {
        return listaProdServ;
    }

    public void setListaProdServ(List<Object> listaProdServ) {
        this.listaProdServ = listaProdServ;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
