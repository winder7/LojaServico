package com.waal.negocio;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 * @Autor Winder Rezende
 * @Data 12/05/2019, 13:06:34
 */
@ManagedBean
@ViewScoped
public class WizardCtrl implements Serializable {

    private boolean skip;

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirmacao";
        } else {
            return event.getNewStep();
        }
    }
}
