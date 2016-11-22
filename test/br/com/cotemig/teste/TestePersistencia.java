/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotemig.teste;

import br.com.cotemig.Persistencia.*;
import br.com.cotemig.condominio.Morador;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Informatica - ASSEMP
 */
public class TestePersistencia {
    
    public TestePersistencia() {
    }
    
    
    
    @Before
    public void PreparaAmbiente() {
        Usuario user = new Usuario();
        FabricaConexoes fab = new FabricaConexoes();
        Morador morador = new Morador();
        
        
    }
    
     @Test
     public void TestaConexaoUsuarioBD() {
         UsuarioDAO userBD = new UsuarioDAO(FabricaConexoes.Conexao());
         assertTrue(userBD == null);
     }
}
