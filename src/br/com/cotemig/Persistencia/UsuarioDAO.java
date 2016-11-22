/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotemig.Persistencia;

import com.sun.management.VMOption;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.JMX;
import javax.swing.JOptionPane;

/**
 *
 * @author Informatica - ASSEMP
 */
public class UsuarioDAO {

    private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;

    }

    public void salvar(Usuario usuario) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String user = usuario.getUser();
        String senha = CriptografarSenha(usuario.getSenha());
        String sql = "insert into USUARIO (LOGIN,SENHA) values ( ? , ? )";
        try {

            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, senha);

            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao criar Novo Usuário");
        }

    }

    public boolean ValidarUsuario(Usuario usuario) throws UnsupportedEncodingException {
        boolean valida = false;
        String user = usuario.getUser();
        String senha = CriptografarSenha(usuario.getSenha());
        String userBD = null;
        String senhaBD = null;
        String sql = "select * from USUARIO where LOGIN = ? AND  SENHA = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userBD = rs.getString("LOGIN");
                senhaBD = rs.getString("SENHA");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user.equals(userBD) && senha.equals(senhaBD)) {

            valida = true;
        }

        return valida;
    }

    public String CriptografarSenha(String senha) throws UnsupportedEncodingException {
        String passwordEncrypted = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(senha.getBytes("UTF-8"));
            BigInteger bigInteger = new BigInteger(1, md.digest());
            passwordEncrypted = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passwordEncrypted;
    }

}
