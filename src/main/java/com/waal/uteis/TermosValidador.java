package com.waal.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @Autor Winder Rezende
 * @Data  06/06/2019
 */

@FacesValidator(value = "termosValidador")
public class TermosValidador implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        boolean termos = (boolean) o;
    
        System.out.println(termos);
        try {
            if (!termos) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Você deve aceitar os termos antes de finalizar o pedido!", null));
            }
        } catch (Exception e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }
}
