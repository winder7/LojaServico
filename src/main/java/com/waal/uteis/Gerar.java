package com.waal.uteis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
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
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;

/**
 * @Autor Winder Rezende
 * @Data 28/10/2018
 */
public class Gerar {

    public static String Senha() {
        
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVYWXZabcdefghijklmnopqrstuvywxz1234567890!@#$%";

        Random random = new Random();

        String armazenaChaves = "";
        int index = -1;
        for (int i = 0; i < 4; i++) {
            index = random.nextInt(caracteres.length());
            armazenaChaves += caracteres.substring(index, index + 1);
        }
        System.out.println(armazenaChaves);
        return armazenaChaves;
    }
    
    public static double Frete(int min, int max) {
    
        Random random = new Random();
        double numInt = random.nextInt((max - min) + 1) + min;
        double numDec = random.nextDouble();

        return (numInt + numDec);
    }

    public static BoletoViewer Boleto(BoletoDto boletoDto) {

        Cedente cedente = new Cedente("WAAL Software S/A", "04.798.469/0001-79");
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_INTEMEDIUM.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(88368, "1"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setAgencia(new Agencia(2073, "1"));

        //Dados do cliente
        Sacado sacado = new Sacado(boletoDto.getPessoa().getNome(), boletoDto.getPessoa().getCpf().replaceAll("[^0-9]",""));
        Endereco enderecoSac = new Endereco();
        enderecoSac.setUF(UnidadeFederativa.valueOf(boletoDto.getPessoa().getUf()));
        enderecoSac.setLocalidade(boletoDto.getPessoa().getCidade());
        enderecoSac.setCep(new CEP(boletoDto.getPessoa().getCep()));
        enderecoSac.setBairro(boletoDto.getPessoa().getBairro());
        enderecoSac.setLogradouro(boletoDto.getPessoa().getRua());
        sacado.addEndereco(enderecoSac);

        double totalGeral = boletoDto.getPedido().getTotalGeral();
        double desconto = boletoDto.getPedido().getDesconto();
        double valorCobrado = totalGeral - desconto;
        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento(UUID.randomUUID().toString().split("-")[0]);
        titulo.setNossoNumero("99345678912");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(BigDecimal.valueOf(totalGeral));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(Somar.Data(new Date(), 2));
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setAceite(Aceite.A);
        titulo.setDesconto(BigDecimal.valueOf(desconto));
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.valueOf(valorCobrado));

        Boleto boleto = new Boleto(titulo);
        boleto.setLocalPagamento("Pagável em qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("A equipe da WAAL Software agradece pela preferência.");
        boleto.setInstrucao1("NÃO RECEBER APOS 30 DIAS DO VENCIMENTO");
        boleto.setInstrucao2("APÓS VENCIMENTO COBRAR MULTA DE 2,00% MAIS MORA DE 0,03% AO DIA");
        boleto.setInstrucao3("Obtenha mais informações no site www.waalservice.com.br ou pelo SAC 0800 777 21");

        BoletoViewer boletoViewer = new BoletoViewer(boleto);

        return boletoViewer;
    }
}
