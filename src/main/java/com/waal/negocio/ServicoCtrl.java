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
        System.out.println("Entrou");
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

    public void listaImagemsProduto() {

        try {
            ServletContext sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            imagens = ImagemDAO.listByServicos(servico.getId());

            File folder = new File(sContext.getRealPath("/resources/prod_Serv"));
            if (!folder.exists()) {
                folder.mkdirs();
            }

            for (Imagem f : imagens) {
                String nomeArquivo = f.getId() + ".jpg";
                String arquivo = sContext.getRealPath("/resources/prod_Serv") + File.separator + nomeArquivo;

                criaArquivo(f.getImg(), arquivo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void criaArquivo(byte[] bytes, String arquivo) {
        FileOutputStream fos;

        try {
            fos = new FileOutputStream(arquivo);
            fos.write(bytes);

            fos.flush();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
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
