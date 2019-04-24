package com.waal.negocio;

import com.ibm.icu.text.SimpleDateFormat;
import com.waal.beans.Fone;
import com.waal.beans.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.waal.persistencia.PessoaDAO;
import java.util.Date;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Autor Winder Rezende
 * @Data 03/06/2019, 20:57:21
 */
@ManagedBean
@SessionScoped
public class PessoaCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private String filtro = "";
    private Pessoa pessoa = new Pessoa();
    private Fone fone = new Fone();

    public List<Pessoa> getListagem() {
        return PessoaDAO.listagem(filtro);
    }

    public void actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (pessoa.getId() == 0) {
            PessoaDAO.inserir(pessoa);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        } else {
            PessoaDAO.alterar(pessoa);
            context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
        }
    }

    public void actionInserir() {
        pessoa = new Pessoa();
    }

    public void actionExcluir() {
        PessoaDAO.excluir(pessoa);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Excluído com sucesso!"));
    }

    public void actionInserirFone() {
        fone = new Fone();
        fone.setPessoa(pessoa);
        pessoa.getFones().add(fone);
    }

    public void actionExcluirFone() {
        pessoa.getFones().remove(fone);
    }

    public String formatarNumero(double num) {
        return String.format("%,.1f", num) + " %";
    }

    public String formatarData(Date data) {
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }

    public String getUsuarioLogado() {
        String usr = "";
        try {
            UserDetails user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            usr = user.toString();
            int ini = usr.indexOf("Username: ");
            int fim = usr.indexOf("; Password:");
            usr = usr.substring((ini + 10), fim);
        } catch (Exception e) {
            System.out.println("Errrrrrrrrrrrrrrrrrrrroooo" + e);
        }
        return usr;
    }

    //GET-SET
    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Fone getFone() {
        return fone;
    }

    public void setFone(Fone fone) {
        this.fone = fone;
    }
}
