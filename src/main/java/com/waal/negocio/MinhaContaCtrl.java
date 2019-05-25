package com.waal.negocio;

import com.waal.beans.Fone;
import com.waal.beans.Pedido;
import com.waal.beans.Pessoa;
import com.waal.persistencia.PedidoDAO;
import com.waal.uteis.SessionData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Alexandre Almeida
 */
@ManagedBean
@ViewScoped
public class MinhaContaCtrl implements Serializable {

    private String menu = "meusPedidos";
    private Pessoa pessoa = new Pessoa();
    private String tipoPessoa = "PF";
    private Fone fone = new Fone();
    private List<Pedido> lista = new ArrayList<Pedido>();
    
    private String _nome;
    
    public void alteraMenu(String _menu) {
        this.menu = _menu;
    }

    @PostConstruct
    public void construct() {
        this.lista = PedidoDAO.listagem();
        this.pessoa = SessionData.getUsuarioLogado();
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

    //gets e sets
    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public List<Pedido> getLista() {
        return lista;
    }

    public void setLista(List<Pedido> lista) {
        this.lista = lista;
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
}
