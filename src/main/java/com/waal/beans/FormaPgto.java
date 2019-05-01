package com.waal.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Autor Winder Rezende
 * @Data 27/02/2019
 */

@Entity
@Table(name = "forma_pgto")
public class FormaPgto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fpg_id")
    private int id;
    @Column(name = "fpg_descricao", length = 60, nullable = true)
    private String descricao;
    @Column(name = "fpg_num_max_parc", nullable = true)
    private int numMaxParc;
    @Column(name = "fpg_num_padrao_parc", nullable = true)
    private int numPadraoParc;
    @Column(name = "fpg_intervalo_dias", nullable = true)
    private int intervaloDias;
    @Column(name = "fpg_percentual_acres", nullable = true)
    private float percentualAcres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNumMaxParc() {
        return numMaxParc;
    }

    public void setNumMaxParc(int numMaxParc) {
        this.numMaxParc = numMaxParc;
    }

    public int getNumPadraoParc() {
        return numPadraoParc;
    }

    public void setNumPadraoParc(int numPadraoParc) {
        this.numPadraoParc = numPadraoParc;
    }

    public int getIntervaloDias() {
        return intervaloDias;
    }

    public void setIntervaloDias(int intervaloDias) {
        this.intervaloDias = intervaloDias;
    }

    public float getPercentualAcres() {
        return percentualAcres;
    }

    public void setPercentualAcres(float percentualAcres) {
        this.percentualAcres = percentualAcres;
    }
}
