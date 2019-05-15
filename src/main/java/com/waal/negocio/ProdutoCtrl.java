package com.waal.negocio;

import com.waal.uteis.Relatorio;
import java.util.ArrayList;
import com.waal.beans.Imagem;
import com.waal.beans.Produto;
import com.waal.persistencia.ImagemDAO;
import com.waal.persistencia.ProdutoDAO;
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

    public String actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (produto.getId() == 0) {
            ProdutoDAO.inserir(produto);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        } else {
            ProdutoDAO.alterar(produto);
            context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
        }
        return "lista_produto";
    }

    public void actionInserir() {
        produto = new Produto();
        //return "lista_produto";
    }

    public String actionExcluir() {
        ProdutoDAO.excluir(produto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Excluído com sucesso!"));
        return "lista_produto";
    }

    public void gerarRelatorio() {
        Relatorio gerar = new Relatorio();
        gerar.getRelatorio(listaProdutos);
    }

    public String formatarNumero(double num) {
        return String.format("R$ " + "%,.2f", num);
    }
    
    public void salvaImagem() {

        try {
            ImagemDAO.save(imagem);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            imagem = new Imagem();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Imagem adicionada", "Imagem adicionada"));
        }
    }

    public void processFileUpload(FileUploadEvent uploadEvent) {
        System.out.println("Entrou");
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

    public void listaImagemsProduto() {

        try {
            ServletContext sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            imagens = ImagemDAO.listByProdutos(produto.getId());

            File folder = new File(sContext.getRealPath("/temp"));
            if (!folder.exists()) {
                folder.mkdirs();
            }

            for (Imagem f : imagens) {
                String nomeArquivo = f.getId() + ".jpg";
                String arquivo = sContext.getRealPath("/temp") + File.separator + nomeArquivo;

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
