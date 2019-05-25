package com.waal.uteis;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;

/**
 * @Autor Alexandre Almeida
 * @Data 25/05/2019
 */
public class GerarBoleto {

    public static void main(String[] args) {

        Cedente cedente = new Cedente("WAAL Software S/A", "04.798.469/0001-79");
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_INTEMEDIUM.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(88368, "1"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setAgencia(new Agencia(2073, "1"));

        //Dados do cliente
        Sacado sacado = new Sacado("JavaDeveloper Pronto Para Férias", "222.222.222-22");
        Endereco enderecoSac = new Endereco();
        enderecoSac.setUF(UnidadeFederativa.RN);
        enderecoSac.setLocalidade("Natal");
        enderecoSac.setCep(new CEP("59064-120"));
        enderecoSac.setBairro("Grande Centro");
        enderecoSac.setLogradouro("Rua poeta dos programas");
        enderecoSac.setNumero("1");
        sacado.addEndereco(enderecoSac);

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento("123456");
        titulo.setNossoNumero("99345678912");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(BigDecimal.valueOf(0.23));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(new Date());
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setAceite(Aceite.A);
        titulo.setDesconto(new BigDecimal(0.05));
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.ZERO);

        
        Boleto boleto = new Boleto(titulo);
        boleto.setLocalPagamento("Pagável em qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("A equipe da WAAL Software agradece pela preferência.");
        boleto.setInstrucao1("NÃO RECEBER APOS 30 DIAS DO VENCIMENTO");
        boleto.setInstrucao2("APÓS VENCIMENTO COBRAR MULTA DE 2,00% MAIS MORA DE 0,03% AO DIA");
        boleto.setInstrucao3("Obtenha mais informações no site www.waalservice.com.br ou pelo SAC 0800 777 21");

        BoletoViewer boletoViewer = new BoletoViewer(boleto);

        // Gerando o arquivo. No caso o arquivo mencionado será salvo na mesma
        // pasta do projeto. Outros exemplos:
        // WINDOWS: boletoViewer.getAsPDF("C:/Temp/MeuBoleto.pdf");
        // LINUX: boletoViewer.getAsPDF("/home/temp/MeuBoleto.pdf");
        File arquivoPdf = boletoViewer.getPdfAsFile("MeuPrimeiroBoleto.pdf");

        // Mostrando o boleto gerado na tela.
        mostreBoletoNaTela(arquivoPdf);
    }

    /**
     * Exibe o arquivo na tela.
     *
     * @param arquivoBoleto
     */
    private static void mostreBoletoNaTela(File arquivoBoleto) {

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

        try {
            desktop.open(arquivoBoleto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
