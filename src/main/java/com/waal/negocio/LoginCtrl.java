package com.waal.negocio;

import com.waal.beans.Pessoa;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
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
    private String senhaAtual;
    private String novaSenha;

    public void actionGravar() {
        if (PessoaDAO.verifUsrCad(usrEmail, cpf)) {
            pessoa = PessoaDAO.pesqNomeUsr(usrEmail);
            String novaSenha = Gerar.Senha();
            pessoa.setSenha(novaSenha);
            Enviar.Email(usrEmail, "Recuperação de Senha Waal Service", htmlMsg(novaSenha, pessoa.getNome()));
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

    public void alterarSenhaCliente() {
        pessoa = new Pessoa();
        pessoa = SessionData.getUsuarioLogado();
        String senhaCript = pessoa.getSenha();
        if (senhaCript.equals(SessionData.encriptarSenha(senhaAtual))) {
            pessoa.setSenha(SessionData.encriptarSenha(novaSenha));
            PessoaDAO.alterar(pessoa);
            Exibir.Mensagem("Tudo Certo! A senha foi alterada com sucesso.");
        } else {
            Exibir.MensagemErro("Ops! A senha atual não é valida!");
        }
    }

    private String htmlMsg(String novaSenha, String nomePessoa) {
        String msg = "<!DOCTYPE html><html><div style=\"width: 560px; margin: auto;\"><center><img src=\"D:\\imagem\\waalName.jpg\" style=\"width: 550px;\"/>" +
                "<b><h1>Olá " + nomePessoa + "!</h1></b><br>" +
                "<h3>Sua nova senha de acesso é: " + novaSenha + "</h3></center>" +
                "<p><b>OBS: </b> Para sua segurança efetue a alteração da senha após realizar o login no site.</p></div></html>";
        
        return msg;
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

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}
