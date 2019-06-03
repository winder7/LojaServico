package com.waal.negocio;

import com.waal.uteis.Relatorio;
import java.util.ArrayList;
import com.waal.beans.Imagem;
import com.waal.beans.Produto;
import com.waal.persistencia.ImagemDAO;
import com.waal.persistencia.ProdutoDAO;
import com.waal.uteis.Obter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;

/**
 * @Autor Winder Rezende
 * @Data 27/02/2019, 20:45:21
 */
@ManagedBean
@SessionScoped
public class ProdutoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private String filtro = "";
    private Produto produto = new Produto();
    private List<Produto> listaProdutos = new ArrayList<>();
    private List<Imagem> imagens;
    private Imagem imagem = new Imagem();

    public List<Produto> getListagem() {
        listaProdutos = ProdutoDAO.listagem(filtro);
        return listaProdutos;
    }

    public void actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (produto.getId() == 0) {
            ProdutoDAO.inserir(produto);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        } else {
            ProdutoDAO.alterar(produto);
            context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
        }
        listaImagem();
    }

    public void actionInserir() {
        produto = new Produto();
    }

    public void actionExcluir() {
        ProdutoDAO.excluir(produto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Excluído com sucesso!"));
    }

    public void gerarRelatorio() {
        Relatorio gerar = new Relatorio();
        gerar.getRelatorio(listaProdutos);
    }

    public String formatarNumero(double num) {
        return String.format("R$ " + "%,.2f", num);
    }

    public void processFileUpload(FileUploadEvent uploadEvent) {
        try {
            imagem = new Imagem();
            imagem.setProduto(produto);
            imagem.setImg(uploadEvent.getFile().getContents());
            produto.getImagens().add(imagem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void actionExcluirImagem() {
        produto.getImagens().remove(imagem);
    }

    public void listaImagem() {
        imagens = produto.getImagens();
        Obter.Imagens(imagens);
    }

    public void listaTodasImagens() {
        imagens = ImagemDAO.listagem();
        Obter.Imagens(imagens);
    }

    public int getImagem(Produto produto) {
        try {
            return produto.getImagens().get(0).getId();
        } catch (Exception e) {
            System.out.println("Este produto não possui imagem: " + e);
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
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
