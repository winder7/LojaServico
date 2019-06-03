package com.waal.uteis;

import com.waal.beans.Imagem;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 * @Autor Winder Rezende
 * @Data  10/11/2018
 */
public class Obter {

    public static String CaminhoArquivo(String nomeArquivo) {
        String CaminhoArq;
        try {
            // obtem percurso para o arquivo
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            CaminhoArq = ec.getRealPath("/");
            if (CaminhoArq.endsWith("\\") == false) {
                CaminhoArq += "\\";
            }
            CaminhoArq += "resources\\img\\" + nomeArquivo;
            System.out.println(CaminhoArq);
        } catch (Exception ex) {
            System.out.println("Erro Construtor de TextFileOnResource:" + ex.getMessage());
            CaminhoArq = null;
        }
        return CaminhoArq;
    }
    
    public static String CaminhoFotoRel(String nomeArquivo) {
        String CaminhoArq;
        try {
            // obtem percurso para o arquivo
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            CaminhoArq = ec.getRealPath("/");
            if (CaminhoArq.endsWith("\\") == false) {
                CaminhoArq += "\\";
            }
            CaminhoArq += "WEB-INF\\classes\\report\\" + nomeArquivo;
            System.out.println(CaminhoArq);
        } catch (Exception ex) {
            System.out.println("Erro Construtor de TextFileOnResource:" + ex.getMessage());
            CaminhoArq = null;
        }
        return CaminhoArq;
    }
    
    public static void Imagens(List<Imagem> imagens) {
        try {
            ServletContext sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            File folder = new File(sContext.getRealPath("/resources/prod_Serv"));
            if (!folder.exists()) {
                folder.mkdirs();
            }

            for (Imagem img : imagens) {
                String nomeArquivo = img.getId() + ".jpg";
                String arquivo = sContext.getRealPath("/resources/prod_Serv") + File.separator + nomeArquivo;

                criaArquivo(img.getImg(), arquivo);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao listar imagens: " + ex);
        }
    }
    
    private static void criaArquivo(byte[] bytes, String arquivo) {
        FileOutputStream fos;

        try {
            fos = new FileOutputStream(arquivo);
            fos.write(bytes);

            fos.flush();
            fos.close();
        } catch (Exception ex) {
            System.out.println("Erro ao criar imagem: " + ex);
        }
    }
}
