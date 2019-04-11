package com.waal.negocio;

import com.waal.beans.Servico;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.waal.persistencia.ServicoDAO;

/**
 * @Autor Winder Rezende
 * @Data 27/02/2019, 20:45:21
 */
@ManagedBean
@SessionScoped
public class ServicoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private String filtro = "";
    private Servico servico = new  Servico();

    public List<Servico> getListagem() {
        return ServicoDAO.listagem(filtro);
    }

    public String actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (servico.getId() == 0) {
            ServicoDAO.inserir(servico);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        } else {
            ServicoDAO.alterar(servico);
            context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
        }
        return "lista_servico";
    }

    public String actionInserir() {
        servico = new Servico();
        return "lista_servico";
    }

    public String actionExcluir() {
        ServicoDAO.excluir(servico);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Excluído com sucesso!"));
        return "lista_servico";
    }

    //GET-SET
    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
