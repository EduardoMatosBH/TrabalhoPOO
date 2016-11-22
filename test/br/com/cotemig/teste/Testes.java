/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotemig.teste;

import br.com.cotemig.Persistencia.FabricaConexoes;
import br.com.cotemig.Persistencia.MoradorDAO;
import br.com.cotemig.Persistencia.Usuario;
import br.com.cotemig.Persistencia.UsuarioDAO;
import br.com.cotemig.condominio.Morador;
import com.sun.jndi.ldap.Connection;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


/**
 *
 * @author Informatica - ASSEMP
 */
public class Testes {
    
    
    public Usuario user = new Usuario();
    public Morador morador = new Morador();
    public FabricaConexoes fb = new FabricaConexoes();
    public UsuarioDAO udao ;
    public MoradorDAO mdao;
    
    @Test
    public void TestaConexao(){
        udao = new UsuarioDAO(fb.Conexao());
        
        
    }
    
    
}
