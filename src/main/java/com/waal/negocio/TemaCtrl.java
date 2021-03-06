package com.waal.negocio;

import com.waal.persistencia.PessoaDAO;
import com.waal.uteis.SessionData;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @Autor Winder Rezende
 * @Data 19/03/2019, 22:17:39
 */
@ManagedBean
@SessionScoped
public class TemaCtrl implements Serializable {

    private String tema = "omega";

    public String formatarNumero(double num) {
        return String.format("R$ " + "%,.2f", num);
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String[] getTemas() {
        return new String[]{
            "afterdark", "afternoon", "afterwork", "aristo",
            "black-tie", "blitzer", "bluesky", "bootstrap", "casablanca",
            "cupertino", "cruze", "dark-hive", "delta", "dot-luv",
            "eggplant", "excite-bike", "flick", "glass-x", "home",
            "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
            "overcast", "pepper-grinder", "redmond", "rocket", "sam",
            "smoothness", "south-street", "start", "sunny", "swanky-purse",
            "trontastic", "ui-darkness", "ui-lightness", "vader"};
    }

    public String verifClienteLogado() {
        if (SessionData.estaLogado()) {
            return "/cliente/cesta.xhtml";
        } else {
            return "/publico/cesta.xhtml";
        }
    }

    public String obterUrl() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest servletRequest = (HttpServletRequest) fc.getExternalContext().getRequest();
        String fullURI = servletRequest.getRequestURI();
        //System.out.println(fullURI);

        if (fullURI.contains("produtos")) {
            return "produtos";
        } else if (fullURI.contains("servicos")) {
            return "servicos";
        } else if (fullURI.contains("index")) {
            return "index";
        }
        return "null";
    }
}
