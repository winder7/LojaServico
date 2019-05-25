package com.waal.negocio;

import com.waal.beans.Fone;
import com.waal.beans.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import com.waal.persistencia.PessoaDAO;
import com.waal.uteis.BuscaCEP;
import com.waal.uteis.CepDTO;
import com.waal.uteis.SessionData;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.SessionScoped;

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
    private boolean editar = false;
    public List<Pessoa> getListagem() {
        return PessoaDAO.listagem(filtro);
    }

    public void actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (pessoa.getId() == 0) {
            pessoa.setSenha(SessionData.encriptarSenha(pessoa.getSenha()));
            if (!usrLogado) {
                pessoa.setTipo("ROLE_CLIENTE");
            }
            PessoaDAO.inserir(pessoa);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        } else {
            pessoa.setSenha(SessionData.encriptarSenha(pessoa.getSenha()));
            PessoaDAO.alterar(pessoa);
            context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
        }
    }

    public void BuscaCep() {
        String cep = pessoa.getCep();
        CepDTO cepDto = new CepDTO();
        if (cep.length() >= 8) {
            cepDto = BuscaCEP.Buscar(cep);
        }
        if (cepDto != null) {
            pessoa.setNome(pessoa.getNome());
            pessoa.setRua(cepDto.getLogradouro());
            pessoa.setBairro(cepDto.getBairro());
            pessoa.setCidade(cepDto.getCidade());
            pessoa.setUf(cepDto.getUf());
        }
    }

    public void actionInserir() {
        pessoa = new Pessoa();
        pessoa.setSituacao(true);
    }

    public void actionExcluir() {
        PessoaDAO.excluir(pessoa);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Excluído com sucesso!"));
    }

    public void actionInserirFone() {
        fone = new Fone();
        fone.setPessoa(pessoa);
        fone.setDescricao("Celular");
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
        String nome = SessionData.getNomeUsuarioLogado();
        usrLogado = !"Olá Visitante!".equals(nome);
        return nome;
    }
    
    public boolean mostrarBotaoMinhaConta(){
        return (!getUserAdmin() && this.usrLogado);
    }

    public boolean getUserAdmin() {
        return SessionData.ehUserAdmin();
    }

    public String erroLoin(String erro) {
        if (erro.contains("User is disabled")) {
            erro = "Seu usuário foi desabilitado...";
        } else if (erro.contains("Bad credentials")) {
            erro = "Credenciais inválidas..";
        }
        return erro;
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

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
}
