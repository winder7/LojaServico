package com.waal.negocio;

import com.waal.beans.Fone;
import com.waal.beans.ItensPed;
import com.waal.beans.Pedido;
import com.waal.beans.Pessoa;
import com.waal.beans.ProdutoServico;
import com.waal.persistencia.ItensPedDAO;
import com.waal.persistencia.PedidoDAO;
import com.waal.uteis.SessionData;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @Autor Alexandre Almeida
 */
@ManagedBean
@SessionScoped
public class MinhaContaCtrl implements Serializable {

    private String menu = "meusPedidos";
    private Pessoa pessoa = new Pessoa();
    private String tipoPessoa = "PF";
    private Fone fone = new Fone();
    private List<Pedido> listaPedido = new ArrayList<>();
    private Pedido pedido = new Pedido();
    private List<ProdutoServico> listaProdServ = new ArrayList<>();
    private ProdutoServico prodSeerv = new ProdutoServico();
    private List<ItensPed> listaItensPed = new ArrayList<>();
    private ItensPed itensPed = new ItensPed();
    
    private String _nome;
    
    public void alteraMenu(String _menu) {
        this.menu = _menu;
        System.out.println(menu);
    }

    @PostConstruct
    public void construct() {
        this.listaPedido = PedidoDAO.listagem();
        this.pessoa = SessionData.getUsuarioLogado();
    }
    
    public List<Pedido> getListagem() {
        return PedidoDAO.listagemUsr(SessionData.getUsuarioLogado().getId());
    }
    
    public String getNome() {
        return _nome;
    }

    public void setNome(String _nome) {
        this._nome = _nome;
    }
    
    public void actionExcluirFone() {
        pessoa.getFones().remove(fone);
    }
    
    public void ItensPedido(Pedido pedido) {
        listaItensPed = pedido.getItesPed();
        listaProdServ = new ArrayList<>();
        for (ItensPed itensPed1 : listaItensPed) {
            if (itensPed1.getProduto() != null) {
                listaProdServ.add(new ProdutoServico(itensPed1.getProduto()));
            } else {
                listaProdServ.add(new ProdutoServico(itensPed1.getServico()));
            }
        }
    }
    
    public String formatarNumero(double num) {
        try {
            return String.format("R$ " + "%,.2f", num);
        } catch (Exception e) {
            System.out.println("Erro na formatação de número!: " + e);
            return "Erro";
        }
    }
    
    public String formatarData(Date data) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").format(data);
        } catch (Exception e) {
            System.out.println("Erro na formatação de data!:");
            return "Erro";
        }
    }

    //gets e sets
    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Fone getFone() {
        return fone;
    }

    public void setFone(Fone fone) {
        this.fone = fone;
    }

    public List<Pedido> getListaPedido() {
        return listaPedido;
    }

    public void setListaPedido(List<Pedido> listaPedido) {
        this.listaPedido = listaPedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<ProdutoServico> getListaProdServ() {
        return listaProdServ;
    }

    public void setListaProdServ(List<ProdutoServico> listaProdServ) {
        this.listaProdServ = listaProdServ;
    }

    public ProdutoServico getProdSeerv() {
        return prodSeerv;
    }

    public void setProdSeerv(ProdutoServico prodSeerv) {
        this.prodSeerv = prodSeerv;
    }

    public List<ItensPed> getListaItensPed() {
        return listaItensPed;
    }

    public void setListaItensPed(List<ItensPed> listaItensPed) {
        this.listaItensPed = listaItensPed;
    }

    public ItensPed getItensPed() {
        return itensPed;
    }

    public void setItensPed(ItensPed itensPed) {
        this.itensPed = itensPed;
    }
}
