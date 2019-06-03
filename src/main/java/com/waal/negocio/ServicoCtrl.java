package com.waal.negocio;

import com.waal.beans.Imagem;
import com.waal.beans.Servico;
import com.waal.persistencia.ImagemDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.waal.persistencia.ServicoDAO;
import com.waal.uteis.Obter;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;

/**
 * @Autor Winder Rezende
 * @Data 27/02/2019, 20:45:21
 */
@ManagedBean
@SessionScoped
public class ServicoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private String filtro = "";
    private Servico servico = new Servico();
    private List<Imagem> imagens;
    private Imagem imagem = new Imagem();

    public List<Servico> getListagem() {
        return ServicoDAO.listagem(filtro);
    }

    public void actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (servico.getId() == 0) {
            ServicoDAO.inserir(servico);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        } else {
            ServicoDAO.alterar(servico);
            context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
        }
        listaImagem();
    }

    public void actionInserir() {
        servico = new Servico();
    }

    public void actionExcluir() {
        ServicoDAO.excluir(servico);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Excluído com sucesso!"));
    }

    public void processFileUpload(FileUploadEvent uploadEvent) {
        try {
            imagem = new Imagem();
            imagem.setServico(servico);
            imagem.setImg(uploadEvent.getFile().getContents());
            servico.getImagens().add(imagem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void actionExcluirImagem() {
        servico.getImagens().remove(imagem);
    }

    public void listaImagem() {
        imagens = servico.getImagens();
        Obter.Imagens(imagens);
    }
    
    public void listaTodasImagens() {
        imagens = ImagemDAO.listagem();
        Obter.Imagens(imagens);
    }

    public String formatarNumero(double num) {
        return String.format("R$ " + "%,.2f", num);
    }
    
    public int getImagem(Servico servico) {
        try {
            return servico.getImagens().get(0).getId();
        } catch (Exception e) {
            System.out.println("Este serviço não possui imagem: " + e);
            return 0;
        }
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

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }
}
