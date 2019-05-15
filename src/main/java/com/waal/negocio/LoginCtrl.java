package com.waal.negocio;

import com.waal.beans.Pessoa;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import com.waal.persistencia.PessoaDAO;
import com.waal.uteis.Enviar;
import com.waal.uteis.Exibir;
import com.waal.uteis.Gerar;
import com.waal.uteis.SessionData;
import javax.faces.bean.RequestScoped;

/**
 * @Autor Winder Rezende
 * @Data 03/06/2019, 20:57:21
 */
@ManagedBean
@RequestScoped
public class LoginCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private Pessoa pessoa;
    private String usrEmail;
    private String cpf;

    public void actionGravar() {
        if (PessoaDAO.verifUsrCad(usrEmail, cpf)) {
            pessoa = PessoaDAO.pesqNomeUsr(usrEmail);
            String novaSenha = Gerar.Senha();
            pessoa.setSenha(novaSenha);
            Enviar.Email(usrEmail, novaSenha);
            if (pessoa.getId() != 0) {
                pessoa.setSenha(SessionData.encriptarSenha(pessoa.getSenha()));
                PessoaDAO.alterar(pessoa);
                System.out.println("Alteraçao efetuada... Nova senha gravada...");
            }
        }
    }

    public void verificarUsrValido() {
        if (PessoaDAO.verifUsrCad(usrEmail, cpf)) {
            Exibir.Mensagem("Tudo Certo!", "Verifique em alguns instantes a caixa de entrada do seu e-mail para recuperar o acesso a sua conta.");
            System.out.println("Tudo Certo! Verifique em alguns instantes a caixa de entrada do seu e-mail para recuperar o acesso a sua conta.");
            actionGravar();
            usrEmail = "";
            cpf = "";
        } else {
            Exibir.MensagemErro("Algo deu errado!", "Os dados informados não foram confirmados! Favor verifique se estão corretos.");
            System.out.println("Os dados informados não foram confirmados! Favor verifique se estão corretos.");
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
