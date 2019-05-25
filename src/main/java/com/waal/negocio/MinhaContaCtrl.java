package com.waal.negocio;

import com.waal.beans.Pedido;
import com.waal.persistencia.PedidoDAO;
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

    private List<Pedido> lista = new ArrayList<Pedido>();

    public void alteraMenu(String _menu) {
        this.menu = _menu;
    }

    @PostConstruct
    public void construct() {
        lista = PedidoDAO.listagem();
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
}
