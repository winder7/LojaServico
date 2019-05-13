package com.waal.negocio;

import com.waal.beans.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import com.waal.persistencia.PessoaDAO;
import com.waal.uteis.Enviar;
import com.waal.uteis.Gerar;
import com.waal.uteis.SessionData;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Winder Rezende
 * @Data 03/06/2019, 20:57:21
 */
@ManagedBean
@RequestScoped
public class LoginCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private String msgVerif = "";
    private Pessoa pessoa;
    private String usrEmail;
    private boolean verificao = false;

    public void actionGravar() {
        if (PessoaDAO.verifEmail(usrEmail)) {
            pessoa = PessoaDAO.pesqNomeUsr(usrEmail);
            String novaSenha = Gerar.Senha();
            pessoa.setSenha(novaSenha);
            Enviar.Email(usrEmail, novaSenha, "Sua nova senha é: ");
            FacesContext context = FacesContext.getCurrentInstance();
            if (pessoa.getId() != 0) {
                pessoa.setSenha(SessionData.encriptarSenha(pessoa.getSenha()));
                PessoaDAO.alterar(pessoa);
                context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
            }
        }
    }

    public void verificarUsrValido() {
        if (PessoaDAO.verifEmail(usrEmail)) {
            verificao = true;
            msgVerif = "Ok! E-mail confirmado.";
            System.out.println(msgVerif);
        } else {
            verificao = false;
            msgVerif = "E-mail invalido!";
            System.out.println(msgVerif);
        }
    }

    //GET-SET
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public boolean isVerificao() {
        return verificao;
    }

    public void setVerificao(boolean verificao) {
        this.verificao = verificao;
    }

    public String getMsgVerif() {
        return msgVerif;
    }

    public void setMsgVerif(String msgVerif) {
        this.msgVerif = msgVerif;
    }
}
