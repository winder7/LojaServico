package com.waal.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * @Autor Winder Rezende
 * @Data 27/02/2019
 */
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private int id;
    @Column(name = "ped_data_emissao", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEmissao;
    @Column(name = "ped_status", length = 25, nullable = true)
    private String status;
    @Column(name = "ped_data_autorizacao", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAutorizacao;
    @Column(name = "ped_total_produto", nullable = true)
    private float totalProduto;
    @Column(name = "ped_total_servico", nullable = true)
    private float totalServico;
    @Column(name = "ped_total_geral", nullable = true)
    private float totalGeral;
    @Column(name = "ped_desconto", nullable = true)
    private float desconto;
    @Column(name = "fpg_descr", length = 80, nullable = true)
    private String fpg_descr;
    @Column(name = "fk_pes_id")
    private int pes_id;
    @Column(name = "fk_fpg_id")
    private int fpg_id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ItensPed> itesPed = new ArrayList<ItensPed>();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataAutorizacao() {
        return dataAutorizacao;
    }

    public void setDataAutorizacao(Date dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }

    public float getTotalProduto() {
        return totalProduto;
    }

    public void setTotalProduto(float totalProduto) {
        this.totalProduto = totalProduto;
    }

    public float getTotalServico() {
        return totalServico;
    }

    public void setTotalServico(float totalServico) {
        this.totalServico = totalServico;
    }

    public float getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(float totalGeral) {
        this.totalGeral = totalGeral;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public int getPes_id() {
        return pes_id;
    }

    public void setPes_id(int pes_id) {
        this.pes_id = pes_id;
    }

    public int getFpg_id() {
        return fpg_id;
    }

    public void setFpg_id(int fpg_id) {
        this.fpg_id = fpg_id;
    }

    public List<ItensPed> getItesPed() {
        return itesPed;
    }

    public void setItesPed(List<ItensPed> itesPed) {
        this.itesPed = itesPed;
    }

    public String getFpg_descr() {
        return fpg_descr;
    }

    public void setFpg_descr(String fpg_descr) {
        this.fpg_descr = fpg_descr;
    }
}
