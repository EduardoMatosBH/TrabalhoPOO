/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotemig.Persistencia;

import com.sun.management.VMOption;
import java.io.UnsupportedEncodingException;
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
import sun.text.normalizer.UTF16;

/**
 *
 * @author Informatica - ASSEMP
 */
public class UsuarioDAO {

    private Connection con;
    private  int User_ID = 2;

    public UsuarioDAO(Connection con) {
        this.con = con;

    }

    public void salvar(Usuario usuario) throws NoSuchAlgorithmException {

        String user = usuario.getUser();
        String senha = CriptografarSenha(usuario.getSenha());
        this.User_ID ++;
        String sql = "insert into USUARIO values ( ? , ? , ?)";
        try {
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, senha);
            ps.setInt(3, User_ID);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao criar Novo Usuário");
        }

    }

    public boolean ValidarUsuario(Usuario usuario) throws NoSuchAlgorithmException {
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

        return (user.equals(userBD) & senha.equals(senhaBD));
    }

    public String CriptografarSenha(String senha){
      
            MessageDigest algorithm;
        try {
                try {
            algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[];
                    messageDigest = algorithm.digest("senha".getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            String sen = hexString.toString();
       
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return senha;
    }
}
