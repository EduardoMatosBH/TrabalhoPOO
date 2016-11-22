/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotemig.Persistencia;

import br.com.cotemig.condominio.Morador;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.text.html.parser.DTDConstants.NUMBER;

/**
 *
 * @author Informatica - ASSEMP
 */
public class MoradorDAO {

    private Connection con;

    public MoradorDAO(Connection con) {
        this.con = con;

    }

    /**
     * Salva um novo morador no BD
     *
     * @param morador
     */
    public boolean salvar(Morador morador) throws SQLException {

        String nome = morador.getNome();
        String cpf = morador.getCpf();
        String situacao = morador.getSituacao();
        String sql = "insert into MORADOR ( NOME, CPF, SITUACAO) values ( ? , ? , ? )";
        try {

            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, cpf);
            ps.setString(3, situacao);

            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao criar Novo Usuário");
        }
        return false;
    }

    /**
     * Atualiza nome, cpf e situação do morador
     *
     * @param morador
     */
    public void atualizar(Morador morador) {

        String cpf = morador.getCpf();
        String nome = morador.getNome();
        String situacao = morador.getSituacao();
        String sql = "update MORADOR set CPF = ? , NOME =  ? , SITUACAO = ?  WHERE CPF = ?";

        try {
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, nome);
            ps.setString(3, situacao);
            ps.setString(4, cpf);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso.");

        } catch (SQLException ex) {
            Logger.getLogger(MoradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Exclui o morador com o cpf do parametro
     *
     * @param cpf
     */
    public boolean excluir(String cpf) {

        String cpf1 = cpf;

        String sql = "DELETE MORADOR WHERE CPF = ?";
        try {

            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, cpf1);

            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir morador");
        }
        return false;
    }

    /**
     * Retorna um List com todos os moradores cadastrados
     *
     * @return List <Morador>
     */
    public ArrayList<Morador> consultar() {
        ArrayList<Morador> lista = new ArrayList<>();
        String sql = "SELECT * FROM MORADOR";
        try {

            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                Morador morador = new Morador();
                morador.setNome(rs.getString("NOME"));
                morador.setCpf(rs.getString("CPF"));
                morador.setSituacao(rs.getString("SITUACAO"));
                morador.setTaxa(rs.getBigDecimal("TAXCONDOMINIO"));
                morador.setVenc(rs.getString("VENCIMENTO"));
                lista.add(morador);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir morador");
        }

        return lista;
    }

    /**
     * Consulta o morador pelo cpf
     *
     * @param cpf
     *
     * @return Retorna um Morador com o cpf do parâmetro
     */
    public Morador consultar(String cpf) {
        String cp = cpf;
        Morador morador = new Morador();
        String sql = "SELECT * FROM MORADOR WHERE CPF = ?";
        try {

            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, cp);

            ps.execute();
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                morador.setNome(rs.getString("NOME"));
                morador.setCpf(rs.getString("CPF"));
                morador.setSituacao(rs.getString("SITUACAO"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao buscar morador");
        }
        return morador;
    }

    /**
     * Consulta o numero de morador existentes
     *
     *
     * @return Retorna a quantidade de moradores do tipo int
     */
    public int qtdeMoradores() {
        int qtde = 0;
        String sql = "SELECT * FROM MORADOR";

        try {

            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                qtde++;

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir morador");
        }

        return qtde;
    }

    public void LancarTaxa(String valor, String vencimento) {

        String l = valor.substring(0, 3) + "" + valor.substring(4, 7) + "" + valor.substring(8, 10);
        BigDecimal g = new BigDecimal(qtdeMoradores());
        BigDecimal lanca  = new BigDecimal(l);
        lanca = lanca.divide(g, BigDecimal.ROUND_UP);
        lanca.setScale(2);
        System.out.println(lanca);
        System.out.println(valor);
        String sql = "update MORADOR set TAXCONDOMINIO = ? , VENCIMENTO = ? ";

        try {
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setBigDecimal(1, lanca);
            ps.setString(2, vencimento);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Taxa lançada com sucesso.");

        } catch (SQLException ex) {
            Logger.getLogger(MoradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizar(ArrayList<Morador> moradores) {
        boolean confirma = true;

        for(Morador x : moradores){
        String sql = "update MORADOR set SITUACAO = ?  WHERE CPF = ?";
        try {
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, x.getSituacao());
            ps.setString(2, x.getCpf());
            ps.execute();
            

        } catch (SQLException ex) {
            Logger.getLogger(MoradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            confirma = false;
        }

    }
        if(confirma)
         JOptionPane.showMessageDialog(null, "Processamento concluído.");
    }
}
