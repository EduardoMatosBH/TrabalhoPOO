/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotemig.condominio;

import br.com.cotemig.Persistencia.FabricaConexoes;
import br.com.cotemig.Persistencia.MoradorDAO;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Informatica - ASSEMP
 */
public class Morador {

    private String nome;
    private String cpf;
    private String ap;
    private String situacao;
    private String venc;
    private BigDecimal taxa;

    public String getVenc() {
        return venc;
    }

    public void setVenc(String venc) {
        this.venc = venc;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public static void ProcessarPgto(ArrayList<String> lista) {

        MoradorDAO mdao = new MoradorDAO(FabricaConexoes.Conexao());

        ArrayList<Morador> moradores = new ArrayList<>();
        moradores = mdao.consultar();
        String chave;
        String cpf;
        String venc;
        String valor;
        BigDecimal x1;

        for (String x : lista) {
            for (int i = 0; i < moradores.size(); i++) {
                chave = x;
                cpf = chave.substring(14, 17) + "." + chave.substring(17, 20) + "." + chave.substring(20, 23) + "-" + chave.substring(23, 25);
                venc = chave.substring(25, 31);
                valor = chave.substring(31, chave.length());
                x1 = new BigDecimal(valor);
                //x1.stripTrailingZeros();
                if (cpf.equals(moradores.get(i).getCpf())) {
                    if ((x1.compareTo(moradores.get(i).getTaxa()) == 0) && (venc.equals(moradores.get(i).getVenc()))) {
                        moradores.get(i).setSituacao("REGULAR");
                    } else {
                        moradores.get(i).setSituacao("IRREGULAR");
                        System.out.println("oi 2");

                    }
                }

            }

        }

                mdao.atualizar(moradores);
    }
}
