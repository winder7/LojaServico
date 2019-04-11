package com.waal.negocio;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

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
}
