package com.waal.negocio;

import com.waal.beans.FormaPgto;
import com.waal.beans.ItensPed;
import com.waal.beans.Pedido;
import com.waal.beans.Pessoa;
import java.util.ArrayList;
import com.waal.beans.Produto;
import com.waal.beans.ProdutoServico;
import com.waal.beans.Servico;
import com.waal.persistencia.FormaPgtoDAO;
import com.waal.persistencia.PedidoDAO;
import com.waal.uteis.BoletoDto;
import com.waal.uteis.Enviar;
import com.waal.uteis.Exibir;
import com.waal.uteis.Gerar;
import com.waal.uteis.Mensagem;
import com.waal.uteis.SessionData;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @Autor Winder Rezende
 * @Data 27/02/2019, 20:45:21
 */
@ManagedBean
@SessionScoped
public class CestaCtrl implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String filtro = "";
    private Produto produto = new Produto();
    private Servico servico = new Servico();
    private List<Produto> listaProdutos = new ArrayList<>();
    private List<Servico> listaServico = new ArrayList<>();
    private List<ProdutoServico> listaProdServ = new ArrayList<>();
    private double somaProduto;
    private double somaServico;
    private double somaProdServ;
    private double somaTotal;
    private double freteNormal;
    private double freteExpersso;
    private double freteEscolhido;
    private double desconto = 0.0;
    private float percentDesconto;
    private String parcelaSel;
    private String formPagSel = "f";
    private String forPagEsc = "p";
    private Map<String, Integer> ItensBoxAno;
    private Map<String, String> ItensBoxParcelas;
    private boolean finalPedido;
    private ItensPed itensPed = new ItensPed();
    public static boolean redirCesta = false;
    private int qtde = 0;
    
    public CestaCtrl() {
        freteNormal = Gerar.Frete(9, 40);
        freteExpersso = freteNormal + Gerar.Frete(1, 15);
        freteEscolhido = freteNormal;
        setBoxAno();
    }
    
    public void addCesta(Produto produto) {
        if (!verifAdd(produto)) {
            listaProdServ.add(new ProdutoServico(produto));
        }
        somaItens();
        Exibir.Mensagem("Sucesso", "Produto: " + produto.getNome() + " adicionado a cesta");
    }
    
    public void addCesta(Servico servico) {
        if (!verifAdd(servico)) {
            listaProdServ.add(new ProdutoServico(servico));
        }
        somaItens();
        Exibir.Mensagem("Sucesso", "Serviço: " + servico.getNome() + " adicionado a cesta");
    }
    
    public void addCesta(ProdutoServico prodServ) {
        if (prodServ.getProduto() != null) {
            addCesta(prodServ.getProduto());
        } else {
            addCesta(prodServ.getServico());
        }
    }
    
    private boolean verifAdd(Produto produto) {
        for (ProdutoServico produtoServico : listaProdServ) {
            if (produtoServico.getProduto() != null) {
                if (produtoServico.getProduto().getId() == produto.getId()) {
                    produtoServico.setQuantidade(produtoServico.getQuantidade() + 1);
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean verifAdd(Servico servico) {
        for (ProdutoServico produtoServico : listaProdServ) {
            if (produtoServico.getServico() != null) {
                if (produtoServico.getServico().getId() == servico.getId()) {
                    produtoServico.setQuantidade(produtoServico.getQuantidade() + 1);
                    return true;
                }
            }
        }
        return false;
    }
    
    public String formatarNumero(double num) {
        return String.format("R$ " + "%,.2f", num);
    }
    
    public void actionExcluir(ProdutoServico itens) {
        listaProdServ.remove(itens);
        somaItens();
        if (itens.getProduto() != null) {
            Exibir.Mensagem("Sucesso", "Produto removido!");
        } else {
            Exibir.Mensagem("Sucesso", "Serviço removido!");
        }
    }
    
    public void removeQtdeZero(ProdutoServico iten) {
        if (iten.getQuantidade() == 0) {
            listaProdServ.remove(iten);
        }
    }
    
    public void actionLimpar() {
        listaProdServ.clear();
        listaProdutos.clear();
        listaServico.clear();
        somaProduto = 0.0;
        somaServico = 0.0;
        somaTotal = 0.0;
        somaProdServ = 0.0;
        desconto = 0.0;
        finalPedido = false;
        redirCesta = false;
        qtde = 0;
    }
    
    public int getImagem(Produto produto) {
        return produto.getImagens().get(0).getId();
    }
    
    public int getImagem(Servico servico) {
        return servico.getImagens().get(0).getId();
    }
    
    public String selecionarIcone(String descricao) {
        
        if (descricao.contains("Boleto")) {
            return "fa fa-barcode";
        } else if (descricao.contains("Cartão")) {
            return "fa fa-credit-card";
        } else if (descricao.contains("Transferência")) {
            return "fa fa-exchange";
        } else {
            return "";
        }
    }
    
    public String selDescricao(String descricao, int parcelas, float percentDesconto) {
        if (parcelas > 1) {
            return descricao + " em " + parcelas + " vezes";
        } else if (descricao.contains("Boleto")) {
            this.percentDesconto = percentDesconto;
            return descricao + " (" + ((int) percentDesconto * -1) + "% de desconto)";
        } else {
            return descricao;
        }
    }
    
    private void setBoxAno() {
        
        ItensBoxAno = new LinkedHashMap<>();
        int ano = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        for (int i = 0; i < 10; i++) {
            ItensBoxAno.put(Integer.toString(ano), (ano++));
        }
    }
    
    public Map<String, String> setBoxParcelas(Map<String, String> ItensBoxParcelas) {
        
        ItensBoxParcelas = new LinkedHashMap<>();
        try {
            List<FormaPgto> parcelas = FormaPgtoDAO.listagem(filtro);
            int parcCartao = 0;
            for (FormaPgto parcela : parcelas) {
                if (parcela.getDescricao().contains("Cartão")) {
                    parcCartao = parcela.getNumMaxParc() + 1;
                }
            }
            System.out.println("Pacelas: " + parcCartao);
            for (int i = 1; i < parcCartao; i++) {
                String parcelaGer = i + "x de " + (formatarNumero(somaTotal / i));
                ItensBoxParcelas.put(parcelaGer, parcelaGer);
            }
        } catch (Exception e) {
            System.out.println("Erro ao gerar parcelas: " + e);
        }
        return ItensBoxParcelas;
    }
    
    public boolean verficaFormaPag(String formaPagamento) {
        
        return forPagEsc.contains(formaPagamento);
    }
    
    public void somaItens() {
        somaProduto = 0;
        somaServico = 0;
        qtde = 0;
        for (ProdutoServico listProdServ : listaProdServ) {
            if (listProdServ.getProduto() != null) {
                somaProduto += listProdServ.getProduto().getPreco() * listProdServ.getQuantidade();
            } else {
                somaServico += listProdServ.getServico().getValor() * listProdServ.getQuantidade();
            }
            qtde += listProdServ.getQuantidade();
        }
        somaProdServ = somaProduto + somaServico;
    }
    
    public double calculaTotal(double somaProdServ, double freteEscolhido) {
        somaTotal = somaProdServ + freteEscolhido + desconto;
        return somaTotal;
    }
    
    public double calculaDesconto(double desconto) {
        forPagEsc = formPagSel;
        if (formPagSel.contains("Boleto")) {
            desconto = somaProdServ * (percentDesconto / 100);
        } else {
            desconto = 0.0;
        }
        this.desconto = desconto;
        return desconto;
    }
    
    public String formaPagInfo(String formaPag) {
        
        if (formaPag.contains("Boleto")) {
            return formaPag.split(";")[0] + " (" + (int) (percentDesconto * -1) + "% desc.)";
        } else if (formaPag.contains("Cartão")) {
            return formaPag.split(";")[0] + " \n" + parcelaSel;
        } else {
            return formaPag.split(";")[0];
        }
    }
    
    public void gerarBoleto() {
        System.out.println("Gerando Boleto...");
        PagamentoCtrl pg = new PagamentoCtrl();
        Pessoa pessoa = new Pessoa();
        pessoa = SessionData.getUsuarioLogado();
        Pedido pedido = new Pedido();
        pedido.setTotalGeral((float) (somaTotal - desconto));
        pedido.setDesconto((float) desconto * -1);
        BoletoDto boletoDto = new BoletoDto(pessoa, pedido);
        pg.GerarBoleto(boletoDto);
        System.out.println("Boleto Gerado!");
    }
    
    public void finalizarPedido() {
        if (finalPedido) {
            try {
                System.out.println("Salvando Pedido...");
                Pedido pedido = new Pedido();
                Pessoa usr = SessionData.getUsuarioLogado();
                pedido.setDataEmissao(new Date());
                pedido.setDataAutorizacao(new Date());
                pedido.setStatus(forPagEsc.contains("Boleto") ? "Aguardando pagamento" : "Pagamento aprovado");
                pedido.setTotalServico((float) somaServico);
                pedido.setTotalProduto((float) somaProduto);
                pedido.setTotalGeral((float) somaTotal);
                pedido.setDesconto((float) desconto);
                pedido.setPes_id(usr.getId());
                pedido.setFpg_id(Integer.parseInt(forPagEsc.split(";")[1]));
                pedido.setFpg_descr(formaPagInfo(forPagEsc));
                pedido.setFrete((float) freteEscolhido);
                for (ProdutoServico prodServ : listaProdServ) {
                    itensPed = new ItensPed();
                    if (prodServ.getProduto() != null) {
                        itensPed.setProduto(prodServ.getProduto());
                        itensPed.setQtde(prodServ.getQuantidade());
                        itensPed.setValorUnit(prodServ.getProduto().getPreco());
                        itensPed.setSubTotal(prodServ.getProduto().getPreco());
                    } else {
                        itensPed.setServico(prodServ.getServico());
                        itensPed.setQtde(prodServ.getQuantidade());
                        itensPed.setValorUnit(prodServ.getServico().getValor());
                        itensPed.setSubTotal(prodServ.getServico().getValor());
                    }
                    itensPed.setPedido(pedido);
                    pedido.getItesPed().add(itensPed);
                }
                
                PedidoDAO.inserir(pedido);
                Enviar.Email(usr.getEmail(), "Pedido realizado na loja Waal Service", Mensagem.html(usr, pedido), null);
                actionLimpar();
                System.out.println("Pedido Salvo... Finalizado!");
            } catch (Exception e) {
                System.out.println("Erro ao finalizar pedido: " + e);
            }
        }
    }
    
    public void addRemQtde(ProdutoServico prodServ, int arg) {
        if (arg > 0) {
            prodServ.setQuantidade(prodServ.getQuantidade() + 1);
        } else {
            prodServ.setQuantidade(prodServ.getQuantidade() - 1);
        }
        if (prodServ.getQuantidade() < 1) {
            listaProdServ.remove(prodServ);
            Exibir.Mensagem("Sucesso", "Item removido!");
        }
        somaItens();
    }
    
    public void ativaRedirCesta(boolean ativaInativa) {
        redirCesta = ativaInativa;
        System.out.println(redirCesta);
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
    
    public Servico getServico() {
        return servico;
    }
    
    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }
    
    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
    
    public List<Servico> getListaServico() {
        return listaServico;
    }
    
    public void setListaServico(List<Servico> listaServico) {
        this.listaServico = listaServico;
    }
    
    public List<ProdutoServico> getListaProdServ() {
        return listaProdServ;
    }
    
    public void setListaProdServ(List<ProdutoServico> listaProdServ) {
        this.listaProdServ = listaProdServ;
    }
    
    public double getSomaProduto() {
        return somaProduto;
    }
    
    public void setSomaProduto(double somaProduto) {
        this.somaProduto = somaProduto;
    }
    
    public double getSomaServico() {
        return somaServico;
    }
    
    public void setSomaServico(double somaServico) {
        this.somaServico = somaServico;
    }
    
    public double getSomaProdServ() {
        return somaProdServ;
    }
    
    public void setSomaProdServ(double somaProdServ) {
        this.somaProdServ = somaProdServ;
    }
    
    public double getSomaTotal() {
        return somaTotal;
    }
    
    public void setSomaTotal(double somaTotal) {
        this.somaTotal = somaTotal;
    }
    
    public double getFreteNormal() {
        return freteNormal;
    }
    
    public void setFreteNormal(double freteNormal) {
        this.freteNormal = freteNormal;
    }
    
    public double getFreteExpersso() {
        return freteExpersso;
    }
    
    public void setFreteExpersso(double freteExpersso) {
        this.freteExpersso = freteExpersso;
    }
    
    public double getFreteEscolhido() {
        return freteEscolhido;
    }
    
    public void setFreteEscolhido(double freteEscolhido) {
        this.freteEscolhido = freteEscolhido;
    }
    
    public double getDesconto() {
        return desconto;
    }
    
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
    
    public float getPercentDesconto() {
        return percentDesconto;
    }
    
    public void setPercentDesconto(float percentDesconto) {
        this.percentDesconto = percentDesconto;
    }
    
    public String getParcelaSel() {
        return parcelaSel;
    }
    
    public void setParcelaSel(String parcelaSel) {
        this.parcelaSel = parcelaSel;
    }
    
    public String getFormPagSel() {
        return formPagSel;
    }
    
    public void setFormPagSel(String formPagSel) {
        this.formPagSel = formPagSel;
    }
    
    public String getForPagEsc() {
        return forPagEsc;
    }
    
    public void setForPagEsc(String forPagEsc) {
        this.forPagEsc = forPagEsc;
    }
    
    public Map<String, Integer> getItensBoxAno() {
        return ItensBoxAno;
    }
    
    public void setItensBoxAno(Map<String, Integer> ItensBoxAno) {
        this.ItensBoxAno = ItensBoxAno;
    }
    
    public Map<String, String> getItensBoxParcelas() {
        return ItensBoxParcelas;
    }
    
    public void setItensBoxParcelas(Map<String, String> ItensBoxParcelas) {
        this.ItensBoxParcelas = ItensBoxParcelas;
    }
    
    public boolean isFinalPedido() {
        return finalPedido;
    }
    
    public void setFinalPedido(boolean finalPedido) {
        this.finalPedido = finalPedido;
    }
    
    public ItensPed getItensPed() {
        return itensPed;
    }
    
    public void setItensPed(ItensPed itensPed) {
        this.itensPed = itensPed;
    }
    
    public static boolean isRedirCesta() {
        return redirCesta;
    }
    
    public static void setRedirCesta(boolean redirCesta) {
        CestaCtrl.redirCesta = redirCesta;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
}
