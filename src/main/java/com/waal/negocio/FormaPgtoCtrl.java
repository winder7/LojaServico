package  com.waal.negocio;

import com.waal.beans.FormaPgto;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.waal.persistencia.FormaPgtoDAO;

/**
 * @Autor Winder Rezende
 * @Data 03/06/2019, 20:57:21
 */
@ManagedBean
@SessionScoped
public class FormaPgtoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private String filtro = "";
    private FormaPgto formaPgto = new FormaPgto();

    public List<FormaPgto> getListagem(){
        return FormaPgtoDAO.listagem(filtro);
    }
    
    public void actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (formaPgto.getId() == 0) {
            FormaPgtoDAO.inserir(formaPgto);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        }else {
            FormaPgtoDAO.alterar(formaPgto);
            context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
        }
    }
    
    public void actionInserir(){
        formaPgto = new FormaPgto();
    }
    
    public void actionExcluir() {
        FormaPgtoDAO.excluir(formaPgto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Excluído com sucesso!"));
    }
    
    public String formatarNumero(double num) {
        return String.format("%,.2f", num) + " %";
    }

    //GET-SET
    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public FormaPgto getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(FormaPgto formaPgto) {
        this.formaPgto = formaPgto;
    }
}