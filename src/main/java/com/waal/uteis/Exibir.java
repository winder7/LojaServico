package com.waal.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @Autor Winder Rezende
 * @Data  29/10/2018
 */
public class Exibir {

    public static void Mensagem(String titulo, String mensagem) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public static void MensagemErro(String titulo, String mensagem) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
}
