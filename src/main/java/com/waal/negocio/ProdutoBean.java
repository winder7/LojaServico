package com.waal.negocio;

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
 * @Data 14/05/2019, 00:44:29
 */
@ManagedBean(name = "mbProduto")
@SessionScoped
public class ProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Produto> produtos;
    private Produto produto = new Produto();
    private Produto produtoSelecionado = new Produto();

    private List<Imagem> imagens;
    private Imagem imagen = new Imagem();

    public ProdutoBean() {
        produtos = ProdutoDAO.listagem("");
    }

    public void salvaProduto() {

        try {
            ProdutoDAO.inserir(produto);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            produto = new Produto();
            produtos = ProdutoDAO.listagem("");

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto adicionado", "Produto adicionado"));
        }
    }

    public void salvaImagem() {

        try {
            ImagemDAO.save(imagen);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            imagen = new Imagem();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Imagem adicionada", "Imagem adicionada"));
        }

    }

    public void processFileUpload(FileUploadEvent uploadEvent) {

        try {
            imagen.setProduto(produtoSelecionado);
            imagen.setImg(uploadEvent.getFile().getContents());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void listaImagemsProduto() {

        try {
            ServletContext sContext = (ServletContext) FacesContext
                    .getCurrentInstance().getExternalContext().getContext();

            imagens = ImagemDAO.listByProdutos(produtoSelecionado.getId());

            File folder = new File(sContext.getRealPath("/temp"));
            if (!folder.exists()) {
                folder.mkdirs();
            }

            for (Imagem f : imagens) {
                String nomeArquivo = f.getId() + ".jpg";
                String arquivo = sContext.getRealPath("/temp") + File.separator
                        + nomeArquivo;

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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public Imagem getImagen() {
        return imagen;
    }

    public void setImagen(Imagem imagen) {
        this.imagen = imagen;
    }
}
