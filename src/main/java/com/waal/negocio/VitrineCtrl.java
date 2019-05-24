package com.waal.negocio;

import java.util.ArrayList;
import com.waal.beans.Produto;
import com.waal.beans.ProdutoServico;
import com.waal.beans.Servico;
import com.waal.persistencia.ProdutoDAO;
import com.waal.persistencia.ServicoDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @Autor Winder Rezende
 * @Data 27/02/2019, 20:45:21
 */
@ManagedBean
@SessionScoped
public class VitrineCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private String filtro = "";

    private Produto produto = new Produto();
    private Servico servico = new Servico();
    private List<Produto> listaProdutos = new ArrayList<>();
    private List<Servico> listaServicos = new ArrayList<>();
    private List<ProdutoServico> listaVitrine = new ArrayList<>();

    public List<ProdutoServico> getListaVitrine() {
        listaProdutos = ProdutoDAO.listagem(filtro);
        listaServicos = ServicoDAO.listagem(filtro);

        listaVitrine.clear();

        listaProdutos.forEach((t) -> {
            listaVitrine.add(new ProdutoServico(t));
        });

        listaServicos.forEach((t) -> {
            listaVitrine.add(new ProdutoServico(t));
        });

        return listaVitrine;
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

    public void setListaVitrine(List<ProdutoServico> listaVitrine) {
        this.listaVitrine = listaVitrine;
    }

    public List<Servico> getListaServicos() {
        return listaServicos;
    }

    public void setListaServicos(List<Servico> listaServicos) {
        this.listaServicos = listaServicos;
    }
}
