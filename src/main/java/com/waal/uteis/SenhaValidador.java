package com.waal.uteis;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @Autor m159255
 * @Data 11/05/2019
 */
@FacesValidator(value = "senhaValidador")
public class SenhaValidador implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String senha = (String) o;
        String confirm = (String) uic.getAttributes().get("confirm");

        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        try {
            if (!senha.equals(confirm)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas devem ser iguais!", null));
            }
            if (senha.length() < 8) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senha precisa conter pelo menos 8 caracteres!", null));
            }
            if (!UpperCasePatten.matcher(senha).find()) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senha precisa conter pelo menos uma letra maiúscula!", null));
            }
            if (!lowerCasePatten.matcher(senha).find()) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senha precisa conter pelo menos uma letra minúscula!", null));
            }
            if (!digitCasePatten.matcher(senha).find()) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senha precisa conter pelo menos um número!", null));
            }
        } catch (Exception e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }
}
