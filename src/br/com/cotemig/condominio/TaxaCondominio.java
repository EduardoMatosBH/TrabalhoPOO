/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotemig.condominio;

import java.math.BigDecimal;

/**
 *
 * @author Informatica - ASSEMP
 */
public class TaxaCondominio {
    private BigDecimal valor;
    private String venc;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getVenc() {
        return venc;
    }

    public void setVenc(String venc) {
        this.venc = venc;
    }
    

    
}
