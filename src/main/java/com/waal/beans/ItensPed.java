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
@Table(name = "itens_pedido")
public class ItensPed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itp_id")
    private int id;
    @Column(name = "itp_qtde", nullable = true)
    private float qtde;
    @Column(name = "itp_valor_unit", nullable = true)
    private float valorUnit;
    @Column(name = "itp_sub_total", nullable = true)
    private float subTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getQtde() {
        return qtde;
    }

    public void setQtde(float qtde) {
        this.qtde = qtde;
    }

    public float getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(float valorUnit) {
        this.valorUnit = valorUnit;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
}
