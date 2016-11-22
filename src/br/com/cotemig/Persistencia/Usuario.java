/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotemig.Persistencia;

import javax.swing.JOptionPane;
import sun.security.util.Password;

/**
 *
 * @author Informatica - ASSEMP
 */
public class Usuario {
    
    private String user;
    private String senha;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    public boolean validarUsuario(Usuario user){
    
    boolean valida = false;
    
    
    return valida;
    
}
    public void MostrarUsuario(){
        JOptionPane.showMessageDialog(null, "Usuário : " + this.getUser() + " Senha : " + this.getSenha());
    }
    
}
