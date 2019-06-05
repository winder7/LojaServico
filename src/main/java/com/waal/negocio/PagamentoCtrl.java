package com.waal.negocio;

import com.waal.beans.FormaPgto;
import com.waal.beans.Pedido;
import com.waal.beans.Pessoa;
import com.waal.persistencia.FormaPgtoDAO;
import com.waal.persistencia.PedidoDAO;
import com.waal.persistencia.PessoaDAO;
import com.waal.uteis.BoletoDto;
import com.waal.uteis.Gerar;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.jrimum.bopepo.view.BoletoViewer;

/**
 * @Autor Alexandre Almeida
 * @Data 25/05/2019
 */
@ManagedBean
@SessionScoped
public class PagamentoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;

    public void GerarBoleto(BoletoDto boletoDto) {
        try {
            BoletoViewer boletoViewer = Gerar.Boleto(boletoDto);
            byte[] pdfAsBytes = boletoViewer.getPdfAsByteArray();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=boleto.pdf");

            OutputStream output = response.getOutputStream();
            output.write(pdfAsBytes);
            response.flushBuffer();

            FacesContext.getCurrentInstance().responseComplete();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @TODO apagar os dois métodos de testes quando implementar corretamente
     */
    public void gerarBoletoTeste(){
        GerarBoleto(CriaDtoTesteBoleto());
    }

    public BoletoDto CriaDtoTesteBoleto() {
        Pessoa pessoa = new Pessoa();
        pessoa = PessoaDAO.listagem("").get(0);
        Pedido pedido = new Pedido();
        pedido = PedidoDAO.listagem().get(0);
        FormaPgto formaPgto = new FormaPgto();
        formaPgto = new FormaPgtoDAO().listagem("").get(0);
        BoletoDto boletoDto = new BoletoDto(pessoa, pedido);

        return boletoDto;
    }
}
