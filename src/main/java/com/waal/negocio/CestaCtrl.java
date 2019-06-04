package com.waal.negocio;

import java.util.ArrayList;
import com.waal.beans.Produto;
import com.waal.beans.ProdutoServico;
import com.waal.beans.Servico;
import com.waal.uteis.Gerar;
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
    private Produto produto2 = new Produto();
    private List<Produto> listaProdutos = new ArrayList<>();
    private List<Servico> listaServico = new ArrayList<>();
    private List<ProdutoServico> listaProdServ = new ArrayList<>();
    private double somaProduto;
    private double somaServico;
    private double somaTotal;
    private double freteNormal;
    private double freteExpersso;
    private double freteEscolhido;
    private String formPagSel;

    public CestaCtrl() {
        freteNormal = Gerar.Frete(9, 40);
        freteExpersso = freteNormal + Gerar.Frete(1, 15);
        freteEscolhido = freteNormal;
    }

    public void addCesta(Produto produto) {
        listaProdServ.add(new ProdutoServico(produto));
        somaProduto += produto.getPreco();
        somaTotal = somaProduto + somaServico;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Produto adicionado a cesta"));
    }

    public void addCesta(Servico servico) {
        listaProdServ.add(new ProdutoServico(servico));
        somaServico += servico.getValor();
        somaTotal = somaProduto + somaServico;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Serviço adicionado a cesta"));
    }

    public void addCesta(ProdutoServico prodServ) {
        if (prodServ.getProduto() != null) {
            addCesta(prodServ.getProduto());
        } else {
            addCesta(prodServ.getServico());
        }
    }

    public String formatarNumero(double num) {
        return String.format("R$ " + "%,.2f", num);
    }
    
    private List<Produto> cestaPordutos() {
        listaProdutos = new ArrayList<>();
        for (ProdutoServico proServ : listaProdServ) {
            if (proServ.getProduto() != null) {
                listaProdutos.add(proServ.getProduto());
            }
        }
        return  listaProdutos;
    } 
    
    private List<Servico> cestaServicos() {
        listaServico = new ArrayList<>();
        for (ProdutoServico proServ : listaProdServ) {
            if (proServ.getServico()!= null) {
                listaServico.add(proServ.getServico());
            }
        }
        return  listaServico;
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
        FacesContext context = FacesContext.getCurrentInstance();
        listaProdServ.remove(itens);
        if (itens.getProduto() != null) {
            this.somaProduto -= itens.getProduto().getPreco();
            somaTotal = somaProduto + somaServico;
            context.addMessage(null, new FacesMessage("Sucesso", "Produto removido!"));
        } else {
            this.somaServico -= itens.getServico().getValor();
            somaTotal = somaProduto + somaServico;
            context.addMessage(null, new FacesMessage("Sucesso", "Serviço Removido!"));
        }
    }

    public void actionLimpar() {
        listaProdServ.clear();
        listaProdutos.clear();
        listaServico.clear();
        somaProduto = 0.0;
        somaServico = 0.0;
        somaTotal = 0.0;
    }

    public int getImagem(Produto produto) {
        return produto.getImagens().get(0).getId();
    }

    public int getImagem(Servico servico) {
        return servico.getImagens().get(0).getId();
    }

    public String selecionarIcone(String descricao) {
        if (descricao.contains("Boleto")) {
            return "fa fa-file-text-o";
        } else if (descricao.contains("Cartão")) {
            return "fa fa-credit-card";
        } else if (descricao.contains("Transferência")) {
            return "fa fa-exchange";
        } else {
            return "";
        }
    }

    public String selDescricao(String descricao, int parcelas) {
        if (parcelas > 1) {
            return descricao + " em " + parcelas + " vezes";
        } else {
            return descricao;
        }
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

    public Produto getProduto2() {
        return produto2;
    }

    public void setProduto2(Produto produto2) {
        this.produto2 = produto2;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public List<Servico> getListaServico() {
        return listaServico;
    }

    public void setListaServico(List<Servico> listaServico) {
        this.listaServico = listaServico;
    }

    public List<ProdutoServico> getListaProdServ() {
        return listaProdServ;
    }

    public void setListaProdServ(List<ProdutoServico> listaProdServ) {
        this.listaProdServ = listaProdServ;
    }

    public double getSomaProduto() {
        return somaProduto;
    }

    public void setSomaProduto(double somaProduto) {
        this.somaProduto = somaProduto;
    }

    public double getSomaServico() {
        return somaServico;
    }

    public void setSomaServico(double somaServico) {
        this.somaServico = somaServico;
    }

    public double getSomaTotal() {
        return somaTotal;
    }

    public void setSomaTotal(double somaTotal) {
        this.somaTotal = somaTotal;
    }

    public double getFreteNormal() {
        return freteNormal;
    }

    public void setFreteNormal(double freteNormal) {
        this.freteNormal = freteNormal;
    }

    public double getFreteExpersso() {
        return freteExpersso;
    }

    public void setFreteExpersso(double freteExpersso) {
        this.freteExpersso = freteExpersso;
    }

    public double getFreteEscolhido() {
        return freteEscolhido;
    }

    public void setFreteEscolhido(double freteEscolhido) {
        this.freteEscolhido = freteEscolhido;
    }

    public String getFormPagSel() {
        return formPagSel;
    }

    public void setFormPagSel(String formPagSel) {
        this.formPagSel = formPagSel;
    }
}
