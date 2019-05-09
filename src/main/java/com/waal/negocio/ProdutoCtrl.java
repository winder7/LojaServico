package com.waal.negocio;

import com.waal.beans.Produto;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.waal.persistencia.ProdutoDAO;
import com.waal.uteis.Relatorio;
import java.util.ArrayList;

/**
 * @Autor Winder Rezende
 * @Data 27/02/2019, 20:45:21
 */
@ManagedBean
@SessionScoped
public class ProdutoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private String filtro = "";
    private Produto produto = new Produto();
    private List<Produto> listaProdutos = new ArrayList<>();

    public List<Produto> getListagem() {
        listaProdutos = ProdutoDAO.listagem(filtro);
        return listaProdutos;
    }

    public String actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (produto.getId() == 0) {
            ProdutoDAO.inserir(produto);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        } else {
            ProdutoDAO.alterar(produto);
            context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
        }
        return "lista_produto";
    }

    public void actionInserir() {
        produto = new Produto();
        //return "lista_produto";
    }

    public String actionExcluir() {
        ProdutoDAO.excluir(produto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Excluído com sucesso!"));
        return "lista_produto";
    }

    public void gerarRelatorio() {
        Relatorio gerar = new Relatorio();
        gerar.getRelatorio(listaProdutos);
    }

    public String formatarNumero(double num) {
        return String.format("R$ " + "%,.2f", num);
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
}
