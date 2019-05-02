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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import org.primefaces.event.FlowEvent;
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
    private boolean usrLogado;
    private String tipopessoa = "PF";

    public List<Pessoa> getListagem() {
        return PessoaDAO.listagem(filtro);
    }

    public void actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (pessoa.getId() == 0) {
            pessoa.setSenha(encriptarSenha(pessoa.getSenha()));
            PessoaDAO.inserir(pessoa);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        } else {
            pessoa.setSenha(encriptarSenha(pessoa.getSenha()));
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
        String nome = "";
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String detalhes = user.toString();
            System.out.println(user);
            if (detalhes.length() > 0) {
                String[] allDetails = detalhes.split(";");
                String[] nomeUsr = allDetails[0].split(":");
                String nomeUsuario = nomeUsr[2].trim();
                System.out.println(nomeUsuario);
                nome = PessoaDAO.pesqNomeUsr(nomeUsuario).getNome();
                System.out.println(nome);
            }
            usrLogado = true;
        } catch (Exception e) {
            usrLogado = false;
            System.out.println("Erro: Nenhum usúario logado! " + e);
            return "Olá Visitante!";
        }
        return nome;
    }

    private String encriptarSenha(String senha) {
        String hash = null;
        try {
            MessageDigest algorithm = MessageDigest.getInstance("sha-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", 0xFF & b));
            }
            hash = hexString.toString();

            System.out.println(hash);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            System.out.println("Erro ao encriptar senha:\n" + ex);
            return null;
        }
        return hash;
    }

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

    public void teste() {
        System.out.println(tipopessoa);
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

    public boolean isUsrLogado() {
        return usrLogado;
    }

    public void setUsrLogado(boolean usrLogado) {
        this.usrLogado = usrLogado;
    }

    public String getTipopessoa() {
        return tipopessoa;
    }

    public void setTipopessoa(String tipopessoa) {
        this.tipopessoa = tipopessoa;
    }
}
